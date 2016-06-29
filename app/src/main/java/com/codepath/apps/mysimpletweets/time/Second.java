package com.codepath.apps.mysimpletweets.time;

import com.ocpsoft.pretty.time.Duration;
import com.ocpsoft.pretty.time.TimeFormat;
import com.ocpsoft.pretty.time.TimeUnit;

import java.util.Locale;

/**
 * Created by evanwild on 6/28/16.
 */
public class Second extends com.ocpsoft.pretty.time.units.Second implements TimeUnit {

    public Second(Locale locale) {
        super(locale);
    }

    @Override
    public TimeFormat getFormat() {
        return new TimeFormat() {
            @Override
            public String format(Duration duration) {
                String str = Math.abs(duration.getQuantity())+"s";
                return str;
            }
        };
    }
}
