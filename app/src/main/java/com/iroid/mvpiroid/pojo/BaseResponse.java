package com.iroid.mvpiroid.pojo;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    @SerializedName("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
