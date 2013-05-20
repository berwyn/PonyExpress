package org.codeweaver.ponyexpress;

import org.codeweaver.ponyexpress.network.Blogger;

import android.app.Application;

/**
 * Created by loki on 19/05/13.
 */
public class PonyExpress extends Application {

	private Blogger	blogger;

	@Override
	public void onCreate() {
		super.onCreate();

		blogger = new Blogger.Builder().build();
	}
}
