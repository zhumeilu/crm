package com.xmg.crm.lucene;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Getter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;
@Component
public class LuceneUtil {
	private IndexWriter  writer;
	private IndexSearcher searcher;
	private IndexReader r;
	private Directory directory;
	@Getter
	private Analyzer analyzer;
	private IndexWriterConfig config;
	//初始化查询对象
	@PostConstruct
	public void searcherInit(){
		try {
			//创建一个目录
			directory=FSDirectory.open(new File("index"));
			//初始化分词器
			analyzer=new IKAnalyzer();
			//初始化配置
			config=new IndexWriterConfig(Version.LATEST, analyzer);
			writer=new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public IndexSearcher getSearcher() {
		//每次获取一个新的查询对象
		try {
			r=DirectoryReader.open(directory);
			searcher=new IndexSearcher(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searcher;
	}

	public void setSearcher(IndexSearcher searcher) {
		this.searcher = searcher;
	}

	public IndexWriter getWriter() {
		return writer;
	}

	public void setWriter(IndexWriter writer) {
		this.writer = writer;
	}
	@PreDestroy
	public void closeWrrter(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
