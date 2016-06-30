package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.TweetSearcher;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by evanwild on 6/27/16.
 */
public class SearchTweetsFragment extends TweetsListFragment {
    private TwitterClient client;
    private String query;

    @Override
    public void onRefresh() {
        clear();
        populateTimeline(query);
    }

    public static SearchTweetsFragment newInstance(String query) {
        Bundle args = new Bundle();
        args.putString("query", query);
        SearchTweetsFragment fragment = new SearchTweetsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the client
        client = TwitterApplication.getRestClient(); // singleton client
        query = getArguments().getString("query");
        populateTimeline(query);
    }


    // Send an API request to get the timeline JSON
    // Fill the listview by creating the Tweet objects from the json
    private void populateTimeline(String query) {
        client.searchTweets(query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                Log.d("DEBUG", json.toString());

                // deserialize
                // create models
                // load model data into listview (gonna need an adapter)
                try {
                    addAll(Tweet.fromJSONArray(json.getJSONArray("statuses")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
