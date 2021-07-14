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

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    public static ArrayList<folder> items=new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.imgbox, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        folder item = items.get(position);
        holder.setItem(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private ImageView iv4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.gtext);
            iv1=itemView.findViewById(R.id.gimg1);
            iv2=itemView.findViewById(R.id.gimg2);
            iv3=itemView.findViewById(R.id.gimg3);
            iv4=itemView.findViewById(R.id.gimg4);
        }

        void setItem(folder data){
            tv.setText(data.getMyname());
        }
    }

}
