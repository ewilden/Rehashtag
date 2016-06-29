package com.codepath.apps.mysimpletweets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    TwitterClient client;

    @BindView(R.id.etCompose) EditText etCompose;
    //@BindView(R.id.btnTweet) Button btnTweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        ButterKnife.bind(this);
        client = TwitterApplication.getRestClient();
    }

    public void onSubmitTweet(View view) {
        String tweetStr = etCompose.getText().toString();
        client.postTweet(tweetStr, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet t = Tweet.fromJSON(response);
                Intent i = new Intent();
                i.putExtra("tweet", t);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
