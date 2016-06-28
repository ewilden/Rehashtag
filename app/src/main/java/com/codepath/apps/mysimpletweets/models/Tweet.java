package com.codepath.apps.mysimpletweets.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by evanwild on 6/27/16.
 */

// Parse the JSON + store the data
    // Encapsulate state/display logic
public class Tweet {
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

}
