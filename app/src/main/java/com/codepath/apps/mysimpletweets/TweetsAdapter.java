package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by evanwild on 6/27/16.
 */
public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> implements TwitterApplication.ImageClickListener {

    private List<Tweet> mTweets;
    private Context mContext;


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        mTweets = tweets;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    // Override and setup custom template


    @Override
    public TweetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tweet t = mTweets.get(position);

        // Populate data into subviews
        holder.tvUserName.setText(t.getUser().getName());
        holder.tvBody.setText(t.getBody());
        holder.tvTimestamp.setText(Tweet.getRelativeTimeAgo(t.getCreatedAt()));

        // clear old image data
        holder.ivProfileImage.setImageResource(0);
        //Glide.with(getContext()).load(t.getUser().getProfileImageUrl()).into(holder.ivProfileImage);
        Glide.with(getContext()).load(t.getUser().getProfileImageUrl())
                .bitmapTransform(new RoundedCornersTransformation(getContext(), 5, 0))
                .into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    @Override
    public void onImageClick(View view, int position) {
        Tweet t = mTweets.get(position);
        Intent i = new Intent(getContext(), ProfileActivity.class);
        i.putExtra("screen_name", t.getUser().getScreenName());
        i.putExtra("user_id", t.getUid());
        getContext().startActivity(i);
    }

    public void onTweetBodyClick(View view, int position) {
        Tweet t = mTweets.get(position);
        Intent i = new Intent(getContext(), DetailActivity.class);
        i.putExtra("tweet", t);
        getContext().startActivity(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
        @BindView(R.id.tvUserName) TextView tvUserName;
        @BindView(R.id.tvBody) TextView tvBody;
        @BindView(R.id.tvTimestamp) TextView tvTimestamp;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ivProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TweetsAdapter.this.onImageClick(v, getAdapterPosition());
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TweetsAdapter.this.onTweetBodyClick(v, getAdapterPosition());
                }
            });
        }
    }

    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        int curSize = this.getItemCount();
        this.notifyItemRangeInserted(curSize, list.size());
    }

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    public void insertFront(Tweet t) {
        mTweets.add(0, t);
        this.notifyDataSetChanged();
    }
}
