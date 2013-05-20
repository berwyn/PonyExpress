package org.codeweaver.ponyexpress.network;

import java.util.Date;

import org.codeweaver.ponyexpress.model.blogger.Blog;
import org.codeweaver.ponyexpress.model.blogger.PostList;
import org.codeweaver.ponyexpress.util.ISO8906;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by loki on 19/05/13.
 */
public interface Blogger {

	public static final String	BASE_URL	= "https://www.googleapis.com/blogger/v3";

	@GET("/blogs/{id}?key={key}")
	Blog getBlog(@Path("id") String ID, @Path("key") String key);

	@GET("/blogs/{id}?key={key}")
	void getBlog(@Path("id") String ID, @Path("key") String key,
			Callback<Blog> callback);

	@GET("/blogs/byurl?url={url}&key={key}")
	Blog getBlogByUrl(@Path("url") String url, @Path("key") String key);

	@GET("/blogs/byurl?url={url}&key={key}")
	void getBlogByUrl(@Path("url") String url, @Path("key") String key,
			Callback<Blog> callback);

	@GET("/blogs/{blogid}/posts")
	PostList listPosts(@Path("blogid") String blogId,
			@Query("startDate") String startDate,
			@Query("endDate") String endDate,
			@Query("maxResults") String maxResults,
			@Query("pageToken") String pageToken);

	@GET("/blogs/{blogid}/posts")
	void listPosts(@Path("blogid") String blogId,
			@Query("startDate") String startDate,
			@Query("endDate") String endDate,
			@Query("maxResults") String maxResults,
			@Query("pageToken") String pageToken, Callback<PostList> callback);

	public class PostListRequestFactory {

		private String	blogId;
		private String	startDate;
		private String	endDate;
		private String	pageToken;
		private String	fetchBodies;
		private String	labels;
		private String	maxResults;
		private String	maxComments;
		private String	fields;

        public PostListRequestFactory setBlogId(String id) {
            this.blogId = id;
            return this;
        }

		public PostListRequestFactory setStartDate(Date date) {
			this.startDate = ISO8906.toString(date);
			return this;
		}

		public PostListRequestFactory setEndDate(Date date) {
			this.endDate = ISO8906.toString(date);
			return this;
		}

		public PostListRequestFactory setPageToken(String token) {
			this.pageToken = token;
			return this;
		}

		public PostListRequestFactory setMaxResults(int max) {
			this.maxResults = String.valueOf(max);
			return this;
		}

		public PostList execute(Blogger service) {
			return service.listPosts(blogId, startDate, endDate, maxResults, pageToken);
		}

		public void execute(Blogger service, Callback<PostList> callback) {
            service.listPosts(blogId, startDate, endDate, maxResults, pageToken, callback);
		}

	}

}
