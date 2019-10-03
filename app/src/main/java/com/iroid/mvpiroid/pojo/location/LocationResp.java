package com.iroid.mvpiroid.pojo.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iroid.mvpiroid.pojo.BaseResponse;

import java.util.ArrayList;

public class LocationResp extends BaseResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<Location> locations;

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
