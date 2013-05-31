package org.codeweaver.ponyexpress;

import org.codeweaver.ponyexpress.network.Blogger;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by loki on 19/05/13.
 */
public class PonyExpress extends Application {

	private Gson	gson;
	private Blogger	blogger;

	public boolean isDebuggable() {
		return BuildConfig.DEBUG;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		blogger = new Blogger.Builder().build(gson);
	}
}
