package com.example.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fadapter extends RecyclerView.Adapter<fadapter.ViewHolder> {
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
        View itemView = inflater.inflate(R.layout.f_recycle, parent, false);
        return new ViewHolder(itemView, context);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        group item = items.get(position);
        holder.setItem(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context _ctx;
        private TextView tv;
        private ImageView iv;
        group Data;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this._ctx=context;
            tv=itemView.findViewById(R.id.textttt);
            iv=itemView.findViewById(R.id.imv1);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MainActivity activity = (MainActivity)_ctx;
                    activity.MainGroup(Data.getName());
                }
            });
        }

        void setItem(group data){
            Data=data;
            tv.setText(data.getName());
        }

    }

}


