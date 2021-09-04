package com.example.app1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentPage1 extends Fragment {
    private fadapter Fadapter;
    private LinearLayoutManager LinearLayoutManager;

    //서버 연결 쪽
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.219.107:3000";
    private EditText seid;
    private String seidT;
    private View v;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment1,container,false);

        //서버
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //검색 이미지버튼 클릭 시 서버로 검색 정보전달
        ImageButton searchID = (ImageButton) v.findViewById(R.id.seach_ID);

        //이미지 클릭 시
        searchID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlesearch();
            }
        });


        // 리사이클
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView1);
        LinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(LinearLayoutManager);
        Fadapter= new fadapter();
        Fadapter.items.clear();
        //리사이클 보이나 임의의 그룹 추가
        fadapter.items.add(new fgroup("그룹이름1","코드1", ""));
        //fadapter.items.add(new fgroup("그룹이름2","코드2", ""));
        //fadapter.items.add(new fgroup("그룹이름3","코드3", ""));
        recyclerView.setAdapter(Fadapter);

        return v;
    }

    private void handlesearch() {
        seid = v.findViewById(R.id.seach_ID_text); // id 입력 텍스트 가져오기
        if (seid.getText().toString().length() == 0) {//공백일 때 처리할 내용
            Toast.makeText(getView().getContext(), "검색할 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else { //공백이 아닐 때 처리할 내용
            seidT = seid.getText().toString();
            Toast.makeText(getActivity(), seidT, Toast.LENGTH_LONG).show();
            Call<String> call = retrofitInterface.searchGroup(seidT);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    //(아래)값 제대로 들어가나 확인 용
                    //Toast.makeText(getContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                    if (response.code() == 200) {
                        seidT = response.body();
                    }
                    else if(response.code() == 404){
                        Toast.makeText(getActivity(), "404 오류", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}