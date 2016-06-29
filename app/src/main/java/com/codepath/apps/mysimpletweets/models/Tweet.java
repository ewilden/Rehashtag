package com.codepath.apps.mysimpletweets.models;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.time.Hour;
import com.codepath.apps.mysimpletweets.time.Minute;
import com.codepath.apps.mysimpletweets.time.Second;
import com.ocpsoft.pretty.time.PrettyTime;
import com.ocpsoft.pretty.time.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by evanwild on 6/27/16.
 */

// Parse the JSON + store the data
    // Encapsulate state/display logic
public class Tweet implements Parcelable {
    // list out the attributes
    private String body;
    private long uid;
    private User user;
    private String createdAt; // timestamp




    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    // Deserialize the JSON
    // Tweet.fromJSON...
    public static Tweet fromJSON(JSONObject json) {
        Tweet t = new Tweet();
        // Extract the values from the JSON, store them
        try {
            t.body = json.getString("text");
            t.uid = json.getLong("id");
            t.createdAt = json.getString("created_at");
            t.user = User.fromJSON(json.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray json) {
        ArrayList<Tweet> arr = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            try {
                Tweet t = Tweet.fromJSON(json.getJSONObject(i));
                if (t != null) arr.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.body);
        dest.writeLong(this.uid);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.createdAt);
    }

    public Tweet() {
    }

    protected Tweet(Parcel in) {
        this.body = in.readString();
        this.uid = in.readLong();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.createdAt = in.readString();
    }

    public static final Parcelable.Creator<Tweet> CREATOR = new Parcelable.Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel source) {
            return new Tweet(source);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();

            if (System.currentTimeMillis() - dateMillis < DateUtils.DAY_IN_MILLIS /* && false */ ) {
                PrettyTime t = TwitterApplication.getPrettyTime();
                relativeDate = t.format(new Date(dateMillis));
            } else {
                relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
