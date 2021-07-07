package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

public class group extends AppCompatActivity {
    String url;
    String name;

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public group(String name, String url){
        this.name=name;
        this.url=url;
    }
}
