package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentPage2 extends Fragment {
    private GridLayoutManager GridLayoutManager;
    private adapter Gadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v= inflater.inflate(R.layout.fragment2,container,false);
        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).AddGroup();
            }
        });
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        GridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(GridLayoutManager);
        Gadapter= new adapter();
        adapter.items.add(new group("a",""));
        adapter.items.add(new group("b",""));
        adapter.items.add(new group("c",""));
        recyclerView.setAdapter(Gadapter);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



}