package com.xmg.crm.lucene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.xmg.crm.domain.Article;

public class DocUtil {
	
	/**
	 * 将对象转换成document
	 * @param doc
	 * @return
	 */
	public static Document obj2Doc(Article t, Document doc) {
		
		IndexableField id=new Field("id",String.valueOf(t.getId()),getType(true,true,false));
		IndexableField author=new Field("author",t.getAuthor().getNickname(),getType(true,true,true));
		IndexableField title=new Field("title",t.getTitle(),getType(true,true,true));
		IndexableField publishedTime=new Field("publishedTime",String.valueOf(t.getPublishedTime()),getType(true,true,false));
		IndexableField content=new Field("content",t.getContent(),getType(true,true,true));
		doc.add(id);
		doc.add(author);
		doc.add(title);
		doc.add(publishedTime);
		doc.add(content);
		return doc;
	}
	/**
	 * 将Document对象转换成Article对象
	 * @param doc
	 * @return
	 */
	public static Map doc2Map(Document doc,Analyzer analyzer,Query query){
		Formatter formatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");//为搜索的内容加上前缀和后缀
		Scorer scorer = new QueryScorer(query);
		Highlighter hl = new Highlighter(formatter,scorer);
		try {
			String id = doc.get("id");
			String title = hl.getBestFragment(analyzer, "title",  doc.get("title"));
			String content_highlight = hl.getBestFragment(analyzer, "content",  doc.get("content"));
			String content = doc.get("content");
			String author = hl.getBestFragment(analyzer, "author",  doc.get("author"));
			String publishedTime = hl.getBestFragment(analyzer, "publishedTime",  doc.get("publishedTime"));
			
			Map<String,Object> map=new HashMap<>();
			map.put("title", title==null?doc.get("title"):title);
			map.put("content", content);
			map.put("content_highlight", content_highlight==null?doc.get("content"):content_highlight);
			map.put("author.nickName", author==null?doc.get("author"):author);
			map.put("publishedTime", publishedTime==null?doc.get("publishedTime"):publishedTime);
			map.put("id", id);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
		
		
		
	}
	/**
	 * 获取字段类型
	 * @param index		是否需要索引
	 * @param store		是否需呀存储
	 * @param token		是否需要分词
	 * @return
	 */
	private static FieldType getType(boolean index, boolean store, boolean token) {
		FieldType type=new FieldType();
		type.setIndexed(index);
		type.setStored(store);
		type.setTokenized(token);
		return type;
	}

}
