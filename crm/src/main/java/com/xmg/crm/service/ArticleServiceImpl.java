package com.xmg.crm.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.xmg.crm.domain.Article;
import com.xmg.crm.lucene.DocUtil;
import com.xmg.crm.lucene.LuceneUtil;
import com.xmg.crm.query.ArticleQueryObject;
import com.xmg.crm.query.PageResult;
import com.xmg.crm.query.QueryObject;
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements IArticleService {

	@Autowired
	private LuceneUtil util;
	@Override
	public void save(Article t) {
		super.save(t);
		try {
			//获取索引读取器
			IndexWriter writer =util.getWriter();
			//新建一个文档
			Document doc=new Document();
			doc=DocUtil.obj2Doc(t,doc);
			writer.addDocument(doc);
			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 先根据id查询出相应的文档，然后删除，然后在将新的文档添加到索引库
	 */
	@Override
	public void update(Article t) {
		super.update(t);
		try {
			//获取索引读取器
			IndexWriter writer =util.getWriter();
			//新建一个文档
			Document doc=new Document();
			doc=DocUtil.obj2Doc(t,doc);
			writer.updateDocument(new Term("id",String.valueOf(t.getId())), doc);
			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Article t) {
		super.delete(t);
		try {
			//获取索引读取器
			IndexWriter writer =util.getWriter();
			//删除原来的索引
			writer.deleteDocuments(new Term("id:"+t.getId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PageResult queryForCondition(QueryObject qo) {
		List<Map> result=new ArrayList<>();
		IndexSearcher searcher=util.getSearcher();
		ArticleQueryObject q=(ArticleQueryObject) qo;
		//pageSize*(currentPage-1)
		try {
			//查询条件，查询个数
			int searchNum=qo.getPageSize()*qo.getPageNumber();
			TopDocs topDocs = searcher.search(q.getQuery(),searchNum);
			System.out.println(topDocs.totalHits);
			//总击中数topDocs.totalHits
			Document doc=null;
			int num=topDocs.totalHits>searchNum?searchNum:topDocs.totalHits;
			for(int i=qo.getPageSize()*(qo.getPageNumber()-1);i<num;i++){
				doc=searcher.doc(topDocs.scoreDocs[i].doc);
				result.add(DocUtil.doc2Map(doc,util.getAnalyzer(),q.getQuery()));
			}
			return new PageResult(topDocs.totalHits, result);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
	
}
