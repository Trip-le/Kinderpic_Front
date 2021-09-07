package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPage2 extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.3:3000";
    private GridLayoutManager GridLayoutManager;
    private adapter Gadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.fragment2,container,false);

        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).AddGroup();
            }
        });

        ImageButton seach = (ImageButton) v.findViewById(R.id.seach);
        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"검색", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        // HashMap에 그룹 정보 저장
        HashMap<String, String> map = new HashMap<>();

        map.put("email", MainActivity.p_email);

        Call<String[]> call = retrofitInterface.getgroup(map);

        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                Toast.makeText(getContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    String[] a=response.body();
                    //Log.d("체크",a[0]);

                    RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
                    GridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
                    recyclerView.setLayoutManager(GridLayoutManager);
                    Gadapter= new adapter(getContext(), response.body());

                    recyclerView.setAdapter(Gadapter);
                }
                else if(response.code() == 400){
                    Toast.makeText(getView().getContext(), "불러오기 오류", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //adapter Gadapter= new adapter();
        //Gadapter.items.add(new group(Gname,""));//그룹추가

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}