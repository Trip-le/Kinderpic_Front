package com.example.app1;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fadapter extends RecyclerView.Adapter<fadapter.ViewHolder> {
    public static ArrayList<fgroup> items=new ArrayList<>();
    private Dialog dialog;
    private Context context;

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.context=parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = (View) inflater.inflate(R.layout.f_recycle, parent, false);
        return new ViewHolder(itemView, context);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        fgroup item = items.get(position);
        holder.setItem(item);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(holder._ctx);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment1_dialog);
                showDialog2(item, position, holder);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context _ctx;
        private TextView tv;
        private TextView iv;
        fgroup Data;




        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this._ctx=context;
            tv=itemView.findViewById(R.id.group_Na);
            iv=itemView.findViewById(R.id.group_Code);

            /*
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    MainActivity activity = (MainActivity)_ctx;
                    activity.MainGroup(Data.getName());
                }
                public void onClick(View v){

                }
            });*/

        }
        void setItem(fgroup data){
            Data=data;
            iv.setText(data.getCode());
            tv.setText(data.getName());
        }

    }
    public void showDialog2(fgroup item, int position, fadapter.ViewHolder holder){
        dialog.show(); // 다이얼로그 띄우기
        /*
        TextView cen=dialog.findViewById(R.id.cen);
        TextView clear=dialog.findViewById(R.id.clear);
        TextView tv=dialog.findViewById(R.id.Text);
        tv.setText(holder.Data.getName()+" "+tv.getText());
        */

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
                Toast.makeText(context,"가입했습니다.",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}


