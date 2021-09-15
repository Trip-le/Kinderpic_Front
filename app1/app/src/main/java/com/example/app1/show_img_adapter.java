package com.example.app1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class show_img_adapter extends RecyclerView.Adapter<show_img_adapter.ViewHolder> {
    private ArrayList<Uri> mData = null ;
    private Context mContext = null ;

    //생성자
    show_img_adapter(ArrayList<Uri> list, Context context){
        mData=list;
        mContext=context;
    }

    //뷰홀더
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.img_list_box);
        }
    }

    //뷰홀더 객체 생성
    @Override
    public show_img_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View itemView = inflater.inflate(R.layout.show_img_list_box, parent, false) ;
        return new show_img_adapter.ViewHolder(itemView);
    }

    //데이터 표시
    @Override
    public void onBindViewHolder(show_img_adapter.ViewHolder holder, int position) {
        Uri image_uri = mData.get(position) ;

        Glide.with(mContext)
                .load(image_uri)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}
