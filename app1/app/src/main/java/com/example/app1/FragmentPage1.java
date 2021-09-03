package com.example.app1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FragmentPage1 extends Fragment {
    private fadapter Fadapter;
    private LinearLayoutManager LinearLayoutManager;

    @Nullable

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v= inflater.inflate(R.layout.fragment1,container,false);

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView1);
        LinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(LinearLayoutManager);
        Fadapter= new fadapter();
        Fadapter.items.clear();
        fadapter.items.add(new fgroup("그룹이름1","코드1", ""));
        fadapter.items.add(new fgroup("그룹이름2","코드2", ""));
        fadapter.items.add(new fgroup("그룹이름3","코드3", ""));
        recyclerView.setAdapter(Fadapter);


        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}