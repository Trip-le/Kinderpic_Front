package com.example.app1;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    public static String[] items;
    private Dialog dialog;
    private Context context;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.219.107:3000";


    public adapter(Context context, String[] items){
        this.context=context;
        this.items=items;
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        Context context = parent.getContext();
        this.context=parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.group, parent, false);
        return new ViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items[position];
        holder.setItem(item);
        holder.min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(holder._ctx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete);
                showDialog(item, position, holder);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context _ctx;
        private TextView tv;
        private ImageView iv;
        private ImageView min;
        private int sign=0;
        String Data;

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
                    activity.MainGroup(Data);
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

        void setItem(String data){
            Data=data;
            tv.setText(data);
        }

    }

    public void showDialog(String item, int position, ViewHolder holder){
        dialog.show(); // 다이얼로그 띄우기
        TextView cen=dialog.findViewById(R.id.cen);
        TextView clear=dialog.findViewById(R.id.clear);
        TextView tv=dialog.findViewById(R.id.Text);
        tv.setText(item+" "+tv.getText());

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", MainActivity.p_email);
                map.put("groupname", item);
                Call<Void> call = retrofitInterface.delGroup(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context,"삭제하였습니다.",Toast.LENGTH_SHORT).show();
                        List<String> list= new ArrayList(Arrays.asList(items));
                        list.remove(position);
                        items= list.toArray(new String[0]);
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        holder.min.setVisibility(View.GONE);
                        holder.sign=0;
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "실패", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        cen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"취소하였습니다.",Toast.LENGTH_SHORT).show();
                holder.min.setVisibility(View.GONE);
                holder.sign=0;
                dialog.dismiss();
            }
        });
    }

}