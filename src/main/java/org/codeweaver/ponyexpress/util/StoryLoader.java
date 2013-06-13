package org.codeweaver.ponyexpress.util;

import org.codeweaver.ponyexpress.PonyExpress;

import java.util.List;

import retrofit.Callback;

/**
 * Created by BerwynCodeweaver on 11/06/13.
 */
public abstract class StoryLoader<T> {

    protected PonyExpress app;
    protected String nextPageToken;

    public StoryLoader(PonyExpress app) {
        this.app = app;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public abstract void load(Callback<T> callback);
    public abstract void loadMore(Callback<T> callback);

}
