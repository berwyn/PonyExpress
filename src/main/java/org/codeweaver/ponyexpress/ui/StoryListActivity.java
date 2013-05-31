package org.codeweaver.ponyexpress.ui;

import org.codeweaver.ponyexpress.R;
import org.codeweaver.ponyexpress.Source;
import org.codeweaver.ponyexpress.ui.fragment.StoryDetailFragment;
import org.codeweaver.ponyexpress.ui.fragment.StoryListFragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * An activity representing a list of Stories. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StoryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link org.codeweaver.ponyexpress.ui.fragment.StoryListFragment} and the item
 * details (if present) is a
 * {@link org.codeweaver.ponyexpress.ui.fragment.StoryDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link org.codeweaver.ponyexpress.ui.fragment.StoryListFragment.Callbacks}
 * interface to listen for item selections.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class StoryListActivity extends FragmentActivity implements
		StoryListFragment.Callbacks {

    private static final int DEFAULT_SOURCE_ORDINAL = 0;

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean					mTwoPane;

	private ListView				drawerList;
	private DrawerLayout			drawerLayout;
	private ActionBarDrawerToggle	drawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_list);

		if (findViewById(R.id.story_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((StoryListFragment) getSupportFragmentManager().findFragmentById(
					R.id.story_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerList.setAdapter(new SourceAdapter());
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(getString(R.string.app_name));
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(getString(R.string.drawer_open_title));
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
	}

	/**
	 * Callback method from {@link StoryListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(StoryDetailFragment.ARG_ITEM_ID, id);
			StoryDetailFragment fragment = new StoryDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.story_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, StoryDetailActivity.class);
			detailIntent.putExtra(StoryDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) { return true; }
		switch (item.getItemId()) {
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		drawerToggle.onConfigurationChanged(newConfig);
	}

	private void selectItem(int position) {
		drawerList.setItemChecked(position, true);
		drawerLayout.closeDrawer(drawerList);
	}

	private class SourceAdapter extends BaseAdapter {

		String[]	displayNames;

		public SourceAdapter() {
			super();
			displayNames = getResources().getStringArray(Source.STRING_RES);
		}

		@Override
		public int getCount() {
			return Source.values().length;
		}

		@Override
		public Source getItem(int i) {
			return Source.values()[i];
		}

		@Override
		public long getItemId(int i) {
			return getItem(i).getId();
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			View convertView = view;
			if (convertView == null) {
				convertView = StoryListActivity.this.getLayoutInflater()
						.inflate(R.layout.drawer_list_item, null);
			}

			TextView tv = (TextView) convertView;
			tv.setText(displayNames[i]);

			return convertView;
		}
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {
			selectItem(position);
		}
	}
}
