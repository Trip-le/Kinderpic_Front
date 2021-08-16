package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app1.R;

public class FragmentPage3_FAQ extends Fragment {
    ImageView qna1;
    ImageView qna2;
    ImageView qna3;
    ImageView qna4;
    LinearLayout answer1;
    LinearLayout answer2;
    LinearLayout answer3;
    LinearLayout answer4;
    ImageButton back;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragmente3__faq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toSetting();
            }
        });

        qna1 = view.findViewById(R.id.qna1);
        answer1 = view.findViewById(R.id.answer1);
        qna1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(answer1.getVisibility()==View.VISIBLE){
                    answer1.setVisibility(View.GONE);
                }
                else{
                    answer1.setVisibility(View.VISIBLE);
                }
            }
        });

        qna2 = view.findViewById(R.id.qna2);
        answer2 = view.findViewById(R.id.answer2);
        qna2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(answer2.getVisibility()==View.VISIBLE){
                    answer2.setVisibility(View.GONE);
                }
                else{
                    answer2.setVisibility(View.VISIBLE);
                }
            }
        });

        qna3 = view.findViewById(R.id.qna3);
        answer3 = view.findViewById(R.id.answer3);
        qna3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(answer3.getVisibility()==View.VISIBLE){
                    answer3.setVisibility(View.GONE);
                }
                else{
                    answer3.setVisibility(View.VISIBLE);
                }
            }
        });

        qna4 = view.findViewById(R.id.qna4);
        answer4 = view.findViewById(R.id.answer4);
        qna4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(answer4.getVisibility()==View.VISIBLE){
                    answer4.setVisibility(View.GONE);
                }
                else{
                    answer4.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    }

