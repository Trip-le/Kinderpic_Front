package com.example.app1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/leave")
    Call<LoginResult> executeLeave (@Body HashMap<String, String> map);

    @POST("/check") //이메일 보내기 (인증번호용)
    Call<CheckResult> executeCheck (@Body HashMap<String, String> map);

    // 프로필 이미지 보내기
    @Multipart
    @POST("/groupimage")
    Call<ImageResult> Image(@Header("Token") String map, @Part List<MultipartBody.Part> Imgs);

    @GET("/groupid")
    Call<String> groupId();

    @POST("/add_group")
    Call<String> addGroup(@Body HashMap<String,String>map);

    @POST("/home")
    Call<String[]> getgroup(@Body HashMap<String,String> map);

    @POST("/group")
    Call<groupResult> showGroup(@Body HashMap<String,String> map);

    @POST("/search_group")
    //Call<String> searchGroup(@Body String string);
    Call<String> searchGroup(@Body HashMap<String,String>map);

    @POST("/add_search_group")
    Call<searchResult> addSearchGroup(@Body HashMap<String,String>map);

}
