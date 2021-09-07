package com.example.app1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    private LinearLayoutManager LinearLayoutManager;

    //서버 연결 쪽
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.3:3000";
    private EditText seid;
    private String searchid;
    private View v;
    //서버에서 받아오기
    public String g_name;

    private RecyclerView recyclerView;
    //다이얼로그 띄우기
    private Dialog dialog;
    private fadapter ffadapter;

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

                seid = v.findViewById(R.id.seach_ID_text); // id 입력 텍스트 가져오기
                if (seid.getText().toString().length() == 0) {//공백일 때 처리할 내용
                    Toast.makeText(getView().getContext(), "검색할 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else { //공백이 아닐 때 처리할 내용
                    searchid = (seid.getText()).toString();
                    //(아래)값 제대로 들어가나 확인 용
                    Toast.makeText(getActivity(), searchid, Toast.LENGTH_LONG).show();

                    HashMap<String, String> map1 = new HashMap<>();
                    map1.put("searchid", searchid);

                    Call<String> call = retrofitInterface.searchGroup(map1);

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code() == 200) {
                                //서버에서 그룹 이름 값 가져오기
                                g_name = response.body();

                                ffadapter= new fadapter(getContext(), searchid, response.body());
                                Toast.makeText(getActivity(), "일치합니다.", Toast.LENGTH_LONG).show();

                                recyclerView.setAdapter(ffadapter);


                            }
                            else if(response.code()==400){
                                //일치하는 항목이 없음
                                //다이얼로그 일치하는 항목 없슴다~

                                //showDialog2(item, position, holder);
                                Toast.makeText(getActivity(), "일치하는 값이 없습니다.", Toast.LENGTH_LONG).show();
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
        });
        // 리사이클
        recyclerView = v.findViewById(R.id.recyclerView1);
        LinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(LinearLayoutManager);


        return v;
    }

    private void handlesearch() {


    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}