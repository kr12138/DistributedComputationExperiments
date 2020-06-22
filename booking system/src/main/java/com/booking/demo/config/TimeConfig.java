package com.booking.demo.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConfig {
    public final static SimpleDateFormat date = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
    public final static String now() {
        return date.format(new Date());
    }
}
