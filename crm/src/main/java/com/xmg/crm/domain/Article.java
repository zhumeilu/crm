package com.xmg.crm.domain;

import java.util.Date;

public class Article extends BaseDomain {
	private String title;
	private Employee author;
	private Date publishedTime;
	private String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Employee getAuthor() {
		return author;
	}
	public void setAuthor(Employee author) {
		this.author = author;
	}
	public Date getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
