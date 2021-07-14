package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

public class folder extends AppCompatActivity {
    String img1;
    String img2;
    String img3;
    String img4;
    String myname;

    public String getimg1(){
        return img1;
    }
    public void setimg1(String img1){
        this.img1=img1;
    }
    public String getimg2(){
        return img2;
    }
    public void setimg2(String img2){
        this.img2=img2;
    }
    public String getimg3(){
        return img3;
    }
    public void setimg3(String img3){
        this.img3=img3;
    }
    public String getimg4(){
        return img4;
    }
    public void setimg4(String img4){
        this.img4=img4;
    }
    public String getMyname(){
        return myname;
    }
    public void setMyname(String myname){
        this.myname=myname;
    }

    public folder(String myname, String img1, String img2, String img3, String img4){
        this.myname=myname;
        this.img1=img1;
        this.img2=img2;
        this.img3=img3;
        this.img4=img4;
    }
}
