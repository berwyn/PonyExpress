package org.codeweaver.ponyexpress.model.blogger;

import org.codeweaver.ponyexpress.PonyExpress;
import org.codeweaver.ponyexpress.util.StoryLoader;

import java.util.List;

import retrofit.Callback;

/**
 * Created by Berwyn Codeweaver on 11/06/13.
 */
public class BloggerStoryLoader extends StoryLoader<PostList> {

    protected String blogId;
    protected String apiKey;

    public BloggerStoryLoader(PonyExpress app, String blogId, String apiKey) {
        super(app);
        this.blogId = blogId;
        this.apiKey = apiKey;
    }

    @Override
    public void load(Callback callback) {
        this.app.getBlogger().listPosts(blogId, apiKey, callback);
    }

    @Override
    public void loadMore(Callback callback) {
        // TODO FIXME Implement load more!
        throw new IllegalStateException("Method not implemented");
    }
}
