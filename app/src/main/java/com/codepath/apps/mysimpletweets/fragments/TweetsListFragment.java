package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TweetsAdapter;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by evanwild on 6/27/16.
 */
public class TweetsListFragment extends Fragment {
    private ArrayList<Tweet> tweets;
    private TweetsAdapter aTweets;
    @BindView(R.id.rvTweets) RecyclerView rvTweets;
    private Unbinder unbinder;


    // inflation logic

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        unbinder = ButterKnife.bind(this, v);
        rvTweets.setAdapter(aTweets);
        rvTweets.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    // creation lifecycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsAdapter(getActivity(), tweets);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }

    public void add(Tweet tweet) {
        aTweets.insertFront(tweet);
        rvTweets.scrollToPosition(0); // TODO this should be equivalent to setSelection in listview
    }

}
