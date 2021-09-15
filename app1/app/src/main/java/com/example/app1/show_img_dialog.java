package com.example.app1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class show_img_dialog extends Dialog {
    private Context context;
    private DialogClickListener dialogClickListener;
    private Activity mainActivity;
    private Button ok,no;
    private RecyclerView recyclerView;
    private show_img_adapter adapter;
    private GridLayoutManager GridLayoutManager;
    private ArrayList<Uri> uriList;

    public show_img_dialog(@NonNull Context context, Activity mainActivity, ArrayList<Uri> uriList, DialogClickListener dialogClickListener){
        super(context);
        this.context=context;
        this.mainActivity=mainActivity;
        this.uriList=uriList;
        this.dialogClickListener=dialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_img_list);

        ok = findViewById(R.id.Yes);
        no = findViewById(R.id.No);
        recyclerView=findViewById(R.id.show_img_recyclerView);

        adapter = new show_img_adapter(uriList, context);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        GridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(GridLayoutManager);

        ok.setOnClickListener(v -> {
            this.dialogClickListener.onPositiveClick();
            dismiss();
        });
        no.setOnClickListener(v -> {
            this.dialogClickListener.onNegativeClick();
            dismiss();
        });
    }
}
