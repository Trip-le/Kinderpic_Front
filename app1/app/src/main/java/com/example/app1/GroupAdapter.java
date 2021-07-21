package com.example.app1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        return new ViewHolder(itemView,context);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        folder item = items.get(position);
        holder.setItem(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        folder Data;
        private TextView tv;
        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private ImageView iv4;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            tv=itemView.findViewById(R.id.gtext);
            iv1=itemView.findViewById(R.id.gimg1);
            iv2=itemView.findViewById(R.id.gimg2);
            iv3=itemView.findViewById(R.id.gimg3);
            iv4=itemView.findViewById(R.id.gimg4);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Toast myToast = Toast.makeText(context,"사진 뷰", Toast.LENGTH_SHORT);
                    myToast.show();
                }
            });
        }

        void setItem(folder data){
            Data=data;
            tv.setText(data.getMyname());
        }
    }

}
