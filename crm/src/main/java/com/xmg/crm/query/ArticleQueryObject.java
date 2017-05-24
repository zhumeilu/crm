package com.xmg.crm.query;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class ArticleQueryObject extends QueryObject {
	private String keyContent;
	
	public String getKeyContent() {
		return keyContent;
	}

	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}
	
	@Override
	protected void customized() {
		if(keyContent!=null){
			String condition = " (obj.title like :keyContent or obj.content like :keyContent) ";
			NameEntry entry = new NameEntry("keyContent","%"+keyContent+"%");
			this.addQuery(condition, entry);
		}
	}

	public Query getQuery(){
		BooleanQuery query = new BooleanQuery();
		if(StringUtils.isBlank(keyContent)){
			query.add(new MatchAllDocsQuery(), Occur.MUST);
		}else{
			QueryParser parse = new QueryParser("content",new IKAnalyzer());
			Query parseQuery = null;
			try {
				parseQuery = parse.parse(keyContent.toLowerCase()+" OR title:"+keyContent.toLowerCase()+" OR author:"+keyContent.toLowerCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			query.add(parseQuery, Occur.MUST);;
		}
		return query;
	}
}
