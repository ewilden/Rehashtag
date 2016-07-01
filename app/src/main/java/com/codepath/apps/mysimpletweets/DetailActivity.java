package com.codepath.apps.mysimpletweets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codepath.apps.mysimpletweets.fragments.DetailActivityFragment;
import com.codepath.apps.mysimpletweets.fragments.SearchTweetsFragment;
import com.codepath.apps.mysimpletweets.models.Tweet;

public class DetailActivity extends AppCompatActivity {
    private Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tweet = getIntent().getParcelableExtra("tweet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            // create UserTimelineFragment
            DetailActivityFragment detailActivityFragment = DetailActivityFragment.newInstance(tweet);
            // display user fragment within the activity (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer, detailActivityFragment);
            ft.commit();
        }
    }

}
