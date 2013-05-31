package org.codeweaver.ponyexpress.model.blogger;

import java.util.List;

/**
 * Created by loki on 19/05/13.
 */
public class PostList {

	/**
	 * Pagination token to retrieve the next set of posts
	 */
	private String		nextPageToken;

	/**
	 * Pagination token to retrieve the previous set of posts
	 */
	private String		previousPageToken;

	/**
	 * The posts returned by the API
	 */
	private List<Post>	items;

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public String getPreviousPageToken() {
		return previousPageToken;
	}

	public void setPreviousPageToken(String previousPageToken) {
		this.previousPageToken = previousPageToken;
	}

	public List<Post> getItems() {
		return items;
	}

	public void setItems(List<Post> items) {
		this.items = items;
	}
}
