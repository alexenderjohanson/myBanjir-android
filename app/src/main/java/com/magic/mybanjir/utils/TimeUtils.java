package com.magic.mybanjir.utils;

import java.util.Date;

/**
 * Created by admin on 10/7/14.
 */
public class TimeUtils {

    public static String getTimePassed(Long timeInMilis){

        Date date = new Date();
        Long currentTime = date.getTime();
        Long seconds = (currentTime - timeInMilis) / 1000;

        if(seconds < 60){
            return String.format("%ds", Math.round(seconds));
        }

        Long minutes = seconds / 60;
        if(minutes < 60){
            return String.format("%dm", Math.round(minutes));
        }

        Long hours = minutes / 60;
        if(hours < 24){
            return String.format("%dh", Math.round(hours));
        }

        Long days = hours / 24;
        return String.format("%dd", Math.round(days));
    }
}
