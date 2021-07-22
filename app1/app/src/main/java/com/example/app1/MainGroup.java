package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainGroup extends Fragment {
    private GridLayoutManager GridLayoutManager;
    private GroupAdapter Gadapter;
    Dialog Dinfo;
    String GName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.maingroup, container, false);

        Bundle bundle=getArguments();
        GName=bundle.getString("name");
        TextView Gname=(TextView)v.findViewById(R.id.Gname);
        Gname.setText(GName);


        Dinfo=new Dialog(container.getContext());
        Dinfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dinfo.setContentView(R.layout.grouppop);

        ImageView info=(ImageView)v.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showinfo();
            }
        });


        ImageButton back = (ImageButton) v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addGroup2();
            }
        });

        FloatingActionButton down = (FloatingActionButton) v.findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"다운로드", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        FloatingActionButton up = (FloatingActionButton) v.findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"업로드", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.grecyclerView);
        GridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(GridLayoutManager);
        Gadapter= new GroupAdapter();
        GroupAdapter.items.clear();
        GroupAdapter.items.add(new folder("name1","","","",""));
        GroupAdapter.items.add(new folder("name2","","","",""));
        GroupAdapter.items.add(new folder("name3","","","",""));
        recyclerView.setAdapter(Gadapter);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void showinfo(){
        Dinfo.show(); // 다이얼로그 띄우기
        TextView tv1=Dinfo.findViewById(R.id.groupn);
        TextView tv2=Dinfo.findViewById(R.id.groupl);
        TextView tv3=Dinfo.findViewById(R.id.groupd);
        TextView tv4=Dinfo.findViewById(R.id.groupf);
        tv1.setText(GName);
        tv2.setText(tv2.getText()+"서울");
        tv3.setText(tv3.getText()+"3.14");
        tv4.setText(tv4.getText()+"a,b,c,d");

        Dinfo.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dinfo.dismiss();
            }
        });
    }


}