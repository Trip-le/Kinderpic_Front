package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app1.R;

public class FragmentPage3 extends Fragment {
    ImageView profile_next;
    ImageView qna;
    ImageView upload_face;
    ImageView notice;
    ImageView service;
    ImageView personal_info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile_next = view.findViewById(R.id.profile_next);
        profile_next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).frag3_profile();
            }
        });

        qna = view.findViewById(R.id.qna);
        qna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).qna();
            }
        });

        upload_face = view.findViewById(R.id.upload_face);
        upload_face.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "계정 사진을 추가합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        notice = view.findViewById(R.id.notice);
        notice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "공지사항입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        service = view.findViewById(R.id.service);
        service.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(), "서비스 이용약관입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        personal_info = view.findViewById(R.id.personal_info);
        personal_info.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity().getApplicationContext(), "개인정보 처리방침입니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

