package org.codeweaver.ponyexpress.network;

import java.util.Date;

import org.codeweaver.ponyexpress.model.blogger.Blog;
import org.codeweaver.ponyexpress.model.blogger.PostList;
import org.codeweaver.ponyexpress.util.ISO8906;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.google.gson.Gson;

/**
 * Created by BerwynCodweaver on 19/05/13.
 */
public interface Blogger {

	public static final String	BASE_URL	= "https://www.googleapis.com/blogger/v3";

	@GET("/blogs/{id}")
	Blog getBlog(@Path("id") String ID, @Query("key") String key);

	@GET("/blogs/{id}")
	void getBlog(@Path("id") String ID, @Query("key") String key,
			Callback<Blog> callback);

	@GET("/blogs/byurl")
	Blog getBlogByUrl(@Query("url") String url, @Query("key") String key);

	@GET("/blogs/byurl")
	void getBlogByUrl(@Query("url") String url, @Query("key") String key,
			Callback<Blog> callback);

	@GET("/blogs/{blogid}/posts")
	PostList listPosts(@Path("blogid") String blogId,
			@Query("key") String apiKey);

	@GET("/blogs/{blogid}/posts")
	void listPosts(@Path("blogid") String blogId,
            @Query("key") String apiKey,
			Callback<PostList> callback);

	@GET("/blogs/{blogid}/posts")
	PostList listPosts(@Path("blogid") String blogId,
			@Query("key") String apiKey,
            @Query("startDate") String startDate,
			@Query("endDate") String endDate,
			@Query("maxResults") String maxResults,
			@Query("pageToken") String pageToken);

	@GET("/blogs/{blogid}/posts")
	void listPosts(@Path("blogid") String blogId,
            @Query("key") String apiKey,
			@Query("startDate") String startDate,
			@Query("endDate") String endDate,
			@Query("maxResults") String maxResults,
			@Query("pageToken") String pageToken, Callback<PostList> callback);

	public class Builder {

		public Blogger build(Gson gson) {
			return new RestAdapter.Builder()
					.setConverter(new GsonConverter(gson)).setServer(BASE_URL)
					.build().create(Blogger.class);
		}

	}

}
