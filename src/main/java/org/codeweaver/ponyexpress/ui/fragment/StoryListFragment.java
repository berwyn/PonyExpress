package org.codeweaver.ponyexpress.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.codeweaver.ponyexpress.PonyExpress;
import org.codeweaver.ponyexpress.R;
import org.codeweaver.ponyexpress.Source;
import org.codeweaver.ponyexpress.dummy.DummyContent;
import org.codeweaver.ponyexpress.model.Story;
import org.codeweaver.ponyexpress.model.blogger.Blog;
import org.codeweaver.ponyexpress.model.blogger.BloggerStoryLoader;
import org.codeweaver.ponyexpress.model.blogger.Post;
import org.codeweaver.ponyexpress.model.blogger.PostList;
import org.codeweaver.ponyexpress.util.StoryLoader;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

/**
 * A list fragment representing a list of Stories. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link StoryDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class StoryListFragment extends ListFragment {

	private static final String	TAG							= "StoryListFragment";

	/**
	 * Key for argument bundles that describes the ordinal for the news item
	 * source
	 */
	public static final String	ARG_KEY_SOURCE				= "src";

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String	STATE_ACTIVATED_POSITION	= "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks			mCallbacks					= sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int					mActivatedPosition			= ListView.INVALID_POSITION;

	private Source				newsSource;
	private NewsSourceAdapter	listAdapter;
	private PonyExpress			app;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks	sDummyCallbacks	= new Callbacks() {
													@Override
													public void onItemSelected(
															String id) {
													}
												};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public StoryListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int sourceOrdinal = getArguments() == null ? 0 : getArguments().getInt(
				ARG_KEY_SOURCE, 0);
		newsSource = Source.values()[sourceOrdinal];
		listAdapter = new NewsSourceAdapter();
		setListAdapter(listAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();

		getActivity().getActionBar().setTitle(newsSource.getDisplayName());
		getListView().setDivider(
				getResources().getDrawable(R.drawable.story_list_divider));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) { throw new IllegalStateException(
				"Activity must implement fragment's callbacks."); }

		mCallbacks = (Callbacks) activity;
		app = (PonyExpress) activity.getApplication();
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	public class NewsSourceAdapter extends BaseAdapter {

		private List<Story>				stories;
		private StoryLoader<PostList>	storyLoader;

		public NewsSourceAdapter() {
			stories = new ArrayList<Story>();

			switch (newsSource.getType()) {
				case BLOGGER:
					configureBlogger();
					break;
			}
		}

		private void configureBlogger() {
			// TODO FIXME Add Blogger production key at some point
			final String apiKey = app.isDebuggable() ? getString(R.string.blogger_debug_key)
					: "";
			app.getBlogger().getBlogByUrl(newsSource.getRootUrl(), apiKey,
					new Callback<Blog>() {
						@Override
						public void success(Blog blog, Response response) {
							if (app.isDebuggable()) {
								Log.d(TAG, "Received blog response:\n"
										+ response.getBody());
							}
							String blogId = String.valueOf(blog.getId());
							storyLoader = new BloggerStoryLoader(app, blogId,
									apiKey);
							getInitialStories();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							// TODO Handle Blog fetch failure
							Log.e(TAG, "Failed to fetch blog:\n"
									+ retrofitError.toString());
						}
					});
		}

		public void addStory(Story story) {
			stories.add(story);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return stories.size();
		}

		@Override
		public Story getItem(int i) {
			return stories.get(i);
		}

		@Override
		public long getItemId(int i) {
			return getItem(i).getID();
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			View convertView = view;
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.story_list_item, null);
			}

			Story post = getItem(i);
			TextView title = (TextView) convertView
					.findViewById(R.id.story_item_title);
			TextView date = (TextView) convertView
					.findViewById(R.id.story_item_date_label);
			ImageView img = (ImageView) convertView
					.findViewById(R.id.story_item_image);

			String imgSrc = null;
			Elements imgElements = Jsoup.parse(post.getContent()).select("img");
			if (imgElements.size() > 0) {
				imgSrc = imgElements.first().attr("src");
			}

			title.setText(post.getTitle());
			date.setText(DateFormat.format("MMM dd, yyyy", post.getPublished()));

			if (imgSrc != null) {
				Ion.with(img).load(imgSrc);
			} else {
				img.setVisibility(View.GONE);
				view.invalidate();
			}

			return convertView;
		}

		private Callback generateCallback() {
			Callback callback;
			switch (newsSource) {
				case EQUESTRIA_DAILY:
				default:
					callback = new Callback<PostList>() {
						@Override
						public void success(PostList o, Response response) {
							stories.addAll(o.getItems());
							notifyDataSetChanged();
						}

						@Override
						public void failure(RetrofitError retrofitError) {
							// TODO FIXME
						}
					};
					break;

			}
			return callback;
		}

		public void getInitialStories() {
			storyLoader.load(generateCallback());
		}

		public void getMoreStories() {
			storyLoader.loadMore(generateCallback());
		}
	}
}
