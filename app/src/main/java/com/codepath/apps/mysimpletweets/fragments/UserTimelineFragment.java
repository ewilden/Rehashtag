package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by evanwild on 6/28/16.
 */
public class UserTimelineFragment extends TweetsListFragment {
    private TwitterClient client;
    private String screenName;

    public static UserTimelineFragment newInstance(String screenName) {

        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        UserTimelineFragment fragment = new UserTimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {
        clear();
        populateTimeline(screenName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the client
        client = TwitterApplication.getRestClient(); // singleton client
        screenName = getArguments().getString("screen_name");
        populateTimeline(screenName);
    }

    // Send an API request to get the timeline JSON
    // Fill the listview by creating the Tweet objects from the json
    private void populateTimeline(String screenName) {
        client.getUserTimeline(screenName, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());

                // deserialize
                // create models
                // load model data into listview (gonna need an adapter)
                addAll(Tweet.fromJSONArray(json));

                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
