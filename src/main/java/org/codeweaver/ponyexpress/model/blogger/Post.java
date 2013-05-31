package org.codeweaver.ponyexpress.model.blogger;

import java.util.Date;

import com.google.gson.JsonObject;

/**
 * Created by loki on 19/05/13.
 */
public class Post {

	private long		id;
	private JsonObject	blog;
	private Date		published;
	private Date		updated;
	private String		url;
	private String		selfLink;
	private String		title;
	private String		content;
	private Author		author;
	private String[]	labels;
	private String		customMetaData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JsonObject getBlog() {
		return blog;
	}

	public void setBlog(JsonObject blog) {
		this.blog = blog;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getCustomMetaData() {
		return customMetaData;
	}

	public void setCustomMetaData(String customMetaData) {
		this.customMetaData = customMetaData;
	}
}
