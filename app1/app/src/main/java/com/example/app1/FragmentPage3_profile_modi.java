package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app1.R;

public class FragmentPage3_profile_modi extends Fragment {
    LinearLayout name_modi;
    LinearLayout pic_modi;
    LinearLayout email_send;
    EditText email_ans;
    LinearLayout emailans_check;
    EditText new_pw1;
    EditText new_pw2;
    LinearLayout pw_modi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment3_profile_modi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name_modi = view.findViewById(R.id.name_modi);
        name_modi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"이름을 수정합니다.", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        pic_modi = view.findViewById(R.id.pic_modi);
        pic_modi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"사진을 수정합니다.", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        email_send = view.findViewById(R.id.email_send);
        email_ans = view.findViewById(R.id.email_ans);
        emailans_check = view.findViewById(R.id.emailans_check);
        new_pw1 = view.findViewById(R.id.new_pw1);
        new_pw2 = view.findViewById(R.id.new_pw2);
        pw_modi = view.findViewById(R.id.pw_modi);

        email_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"이메일을 전송합니다.", Toast.LENGTH_SHORT);
                myToast.show();
                email_ans.setVisibility(View.VISIBLE);
                emailans_check.setVisibility(View.VISIBLE);
            }
        });

        emailans_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new_pw1.setVisibility(View.VISIBLE);
                new_pw2.setVisibility(View.VISIBLE);
                pw_modi.setVisibility(View.VISIBLE);
            }
        });

        pw_modi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(new_pw1.getText()==new_pw2.getText()){
                    Toast.makeText(getActivity().getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

