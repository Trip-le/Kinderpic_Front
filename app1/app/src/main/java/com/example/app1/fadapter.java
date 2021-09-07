package com.example.app1;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.app1.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class fadapter extends RecyclerView.Adapter<fadapter.ViewHolder> {
    //public static ArrayList<fgroup> items=new ArrayList<>();
    private Dialog dialog;
    private Context context;
    //서버 연결 쪽
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.3:3000";
    //
    private String[] gname={"1"};
    private String[] gid={"1"};



    @Override
    public int getItemCount() {
        return gname.length;
    }

    public fadapter(Context context, String gid, String gname){
        this.context=context;
        this.gid[0]=gid;
        this.gname[0]=gname;
        Log.d("채크",gid);
        Log.d("채크",gname);
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
        View itemView = (View) inflater.inflate(R.layout.f_recycle, parent, false);
        return new ViewHolder(itemView, context);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String gname=this.gname[position];
        String gid=this.gid[position];
        holder.setItem(gname, gid);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(holder._ctx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment1_dialog);
                showDialog2(gid, position, holder);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context _ctx;
        private TextView tv;
        private TextView iv;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this._ctx=context;
            tv=itemView.findViewById(R.id.group_Na);
            iv=itemView.findViewById(R.id.group_Code);
        }
        void setItem(String gname, String gid){
            iv.setText(gid);
            tv.setText(gname);
        }

    }
    public void showDialog2(String gid, int position, fadapter.ViewHolder holder){
        dialog.show(); // 다이얼로그 띄우기
        Button yes=dialog.findViewById(R.id.group_yes);
        Button no=dialog.findViewById(R.id.group_no);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"취소했습니다.",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"1. 가입했습니다.",Toast.LENGTH_SHORT).show();
                //서버 쪽으로 이메일 정보 보내기
                HashMap<String, String> map = new HashMap<>();

                //FragmentPage1 page1= new FragmentPage1();
                //MainActivity main=new MainActivity();
                //LoginActivity activity = new LoginActivity();
                //String b= page1.getgname();
                //String c=fgroup.getName();
                Toast.makeText(context,gid,Toast.LENGTH_SHORT).show();
                map.put("groupid", gid);
                //map.put("email", "c");
                map.put("email", MainActivity.p_email);
                Call <Void> call = retrofitInterface.addSearchGroup(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {

                            Toast.makeText(context, "2. 가입했습니다.", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code()==400){

                            Toast.makeText(context, "참가 실패 .", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(context, "404 오류", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


                /*
                Call<String> call = retrofitInterface.addSearchGroup(map);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 200) {

                            Toast.makeText(context, "2. 가입했습니다.", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code()==400){

                            Toast.makeText(context, "일치하는 값이 없습니다.", Toast.LENGTH_LONG).show();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(context, "404 오류", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                */

                dialog.dismiss();
            }
        });
    }

}


