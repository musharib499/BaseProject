package com.innobles.smartplaykids.network.model;

/**
 * Created by Mushareb Ali on 31,December,2017.
 * ali.musharib1@gmail.com
 */

public class APIError {

    private int statusCode;
    private String message;

    public APIError() {
    }

    public int statusCode() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
