package com.codepath.apps.mysimpletweets.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private Tweet tweet;
    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.tvTimestamp) TextView tvTimestamp;
    @BindView(R.id.tvUserName) TextView tvUserName;
    @BindView(R.id.tvBody) TextView tvBody;
    private Unbinder unbinder;


    public static DetailActivityFragment newInstance(Tweet tweet) {
        
        Bundle args = new Bundle();
        args.putParcelable("tweet", tweet);
        DetailActivityFragment fragment = new DetailActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        tweet = getArguments().getParcelable("tweet");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Populate data into subviews
        Tweet t = tweet;
        tvUserName.setText(t.getUser().getName());
        tvBody.setText(t.getBody());
        tvTimestamp.setText(Tweet.getRelativeTimeAgo(t.getCreatedAt()));
        Glide.with(getContext()).load(t.getUser().getProfileImageUrl()).into(ivProfileImage);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
