package org.codeweaver.ponyexpress;

import org.codeweaver.ponyexpress.network.Blogger;

import retrofit.RestAdapter;
import android.app.Application;

/**
 * Created by loki on 19/05/13.
 */
public class PonyExpress extends Application {

	private RestAdapter	bloggerRestAdapter;
	private Blogger		blogger;

	@Override
	public void onCreate() {
		super.onCreate();

		bloggerRestAdapter = new RestAdapter.Builder().setServer(
				Blogger.BASE_URL).build();
		blogger = bloggerRestAdapter.create(Blogger.class);
	}
}
