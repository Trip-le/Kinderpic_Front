package com.example.app1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    public static ArrayList<group> items=new ArrayList<>();
    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.group, parent, false);
    return new ViewHolder(itemView, context);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        group item = items.get(position);
        holder.setItem(item);
        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                holder.min.setVisibility(View.GONE);
                holder.sign=0;
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context _ctx;
        private TextView tv;
        private ImageView iv;
        private ImageView min;
        private int sign=0;
        group Data;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this._ctx=context;
            tv=itemView.findViewById(R.id.texv);
            iv=itemView.findViewById(R.id.imv);
            min=itemView.findViewById(R.id.minus);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MainActivity activity = (MainActivity)_ctx;
                    activity.MainGroup(Data.getName());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (sign == 0) {
                        min.setVisibility(View.VISIBLE);
                        sign=1;
                    }
                    else{
                        min.setVisibility(View.GONE);
                        sign=0;
                    }
                    return true;
                }
            });

        }

        void setItem(group data){
            Data=data;
            tv.setText(data.getName());
        }

    }

}

