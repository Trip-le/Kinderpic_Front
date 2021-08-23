package com.example.app1;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class ImageResult {
    private ArrayList<MultipartBody.Part> files;


    public ArrayList<MultipartBody.Part> getFiles() {
        return files;
    }

}
