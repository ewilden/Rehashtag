package com.codepath.apps.mysimpletweets.time;

import com.ocpsoft.pretty.time.Duration;
import com.ocpsoft.pretty.time.TimeFormat;
import com.ocpsoft.pretty.time.TimeUnit;

import java.util.Locale;

/**
 * Created by evanwild on 6/28/16.
 */
public class Hour extends com.ocpsoft.pretty.time.units.Hour implements TimeUnit {

    public Hour(Locale locale) {
        super(locale);
    }

    @Override
    public TimeFormat getFormat() {
        return new TimeFormat() {
            @Override
            public String format(Duration duration) {
                String str = Math.abs(duration.getQuantity())+"h";
                return str;
            }
        };
    }
}
