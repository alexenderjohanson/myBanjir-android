package com.magic.mybanjir.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by admin on 12/26/14.
 */
@Parcel
public class RiverLevel {

    @SerializedName("StationId")
    public String stationId;

    @SerializedName("StationName")
    public String stationName;


    @SerializedName("District")
    public String district;

    @SerializedName("RiverBasin")
    public String riverBasin;

    @SerializedName("LastUpdateDate")
    public String lastUpdateDate;

    @SerializedName("RiverLevel")
    public Float riverLevel;

    @SerializedName("NormalLevel")
    public Float normalLevel;

    @SerializedName("AlertLevel")
    public Float alertLevel;

    @SerializedName("WarningLevel")
    public Float warningLevel;

    @SerializedName("DangerLevel")
    public Float dangerLevel;

}
