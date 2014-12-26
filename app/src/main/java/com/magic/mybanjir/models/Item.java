package com.magic.mybanjir.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 12/26/14.
 */
@Parcel
public class Item {

    @SerializedName("ID")
    public Integer id;

    @SerializedName("Title")
    public String title;

    @SerializedName("body")
    public String body;

    @SerializedName("Image")
    public String image;

    @SerializedName("Slug")
    public String slug;

    @SerializedName("Timestamp")
    public String dateTime;

    @SerializedName("Source")
    public String soruce;

    @SerializedName("Url")
    public String url;

    @SerializedName("Description")
    public String description;

    @SerializedName("Location")
    public String location;

    public Date getDateTime() {

        String[] dateTimeSplit = this.dateTime.split("\\.");

        if(dateTimeSplit.length < 1){
            return new Date();
        }

        String dateTimeStr = dateTimeSplit[0].replace("T", " ");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date();
    }
}
