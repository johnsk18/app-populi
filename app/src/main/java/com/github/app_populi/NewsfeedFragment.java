package com.github.app_populi;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

import com.github.app_populi.R;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;


/**
 * Created by woolf on 3/21/2018.
 */

public class NewsfeedFragment  extends Fragment{

    private static final String baseURl = "http://twitter.com";
    private static String twitterAccount;
    private static String widgetInfo;


    public static NewsfeedFragment newInstance() {
        NewsfeedFragment fragment = new NewsfeedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        twitterAccount = getResources().getString(R.string.twitter_account);
        widgetInfo = "<a class=\"twitter-timeline\" href=\"https://twitter.com/" + twitterAccount + "?ref_src=twsrc%5Etfw\">Tweets by " + twitterAccount + "</a>" +
                "<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+\"://platform.twitter.com/widgets.js\";fjs.parentNode.insertBefore(js,fjs);}}(document,\"script\",\"twitter-wjs\");</script>";

    /*
        // https://github.com/twitter/twitter-kit-android/wiki/Show-Timelines
        // https://github.com/twitter/twitter-kit-android/blob/master/samples/app/src/main/java/com/example/app/tweetui/UserTimelineFragment.java
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("twitterdev")
                // .screenName(getString(R.string.twitter_account))
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getContext())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);*/

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        WebView webView = (WebView) view.findViewById(R.id.timelineList);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(baseURl, widgetInfo, "text/html", "UTF-8", null);

        return view;
    }



}
