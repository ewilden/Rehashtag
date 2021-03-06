package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.time.Hour;
import com.codepath.apps.mysimpletweets.time.Minute;
import com.codepath.apps.mysimpletweets.time.Second;
import com.ocpsoft.pretty.time.PrettyTime;
import com.ocpsoft.pretty.time.TimeUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 *
 *     TwitterClient client = TwitterApplication.getRestClient();
 *     // use client to send requests to API
 *
 */
public class TwitterApplication extends com.activeandroid.app.Application {
	private static Context context;
	private ArrayList<TimeUnit> unitList;
	private static PrettyTime prettyTime;


	@Override
	public void onCreate() {
		super.onCreate();
		TwitterApplication.context = this;
		unitList  = new ArrayList<>();
		prettyTime = new PrettyTime();
		unitList.add(new Second(Locale.US));
		unitList.add(new Minute(Locale.US));
		unitList.add(new Hour(Locale.US));
		prettyTime.setUnits(unitList);
		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/HelveticaNeueLTPro-Lt.ttf")
				.setFontAttrId(R.attr.fontPath)
				.build()
		);
	}

	public static TwitterClient getRestClient() {
		return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, TwitterApplication.context);
	}

	public interface ImageClickListener {
		public void onImageClick(View view, int position);
	}

	public static PrettyTime getPrettyTime() {
		return prettyTime;
	}



}