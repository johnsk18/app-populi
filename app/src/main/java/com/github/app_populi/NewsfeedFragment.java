package com.github.app_populi;

import android.support.v4.app.ListFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.app_populi.R;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by woolf on 3/21/2018.
 */

public class NewsfeedFragment extends ListFragment {
    public static NewsfeedFragment newInstance() {
        NewsfeedFragment fragment = new NewsfeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // https://github.com/twitter/twitter-kit-android/wiki/Show-Timelines
        // https://github.com/twitter/twitter-kit-android/blob/master/samples/app/src/main/java/com/example/app/tweetui/UserTimelineFragment.java
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("twitterdev")
                // .screenName(getString(R.string.twitter_account))
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false);
    }
}
