package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

public class fgroup extends AppCompatActivity{
    String url;
    String code;
    String name;

    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public fgroup(String name, String code, String url){
        this.name=name;
        this.code=code;
        this.url=url;
    }
}


