package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codepath.apps.mysimpletweets.fragments.SearchTweetsFragment;
import com.codepath.apps.mysimpletweets.fragments.UserTimelineFragment;

public class SearchActivity extends AppCompatActivity {
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the search query string
        query = getIntent().getStringExtra("query");

        if (savedInstanceState == null) {
            // create UserTimelineFragment
            SearchTweetsFragment fragmentSearchTweets = SearchTweetsFragment.newInstance(query);
            // display user fragment within the activity (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, fragmentSearchTweets);
            ft.commit();
        }

    }

}
