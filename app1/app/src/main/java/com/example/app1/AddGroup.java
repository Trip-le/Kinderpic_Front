package com.example.app1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddGroup extends Fragment {
    String Gname;
    String Gday;
    String Gplace;
    String Myname;
    String Fname;
    TextView F;
    private int create=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.add_group,container,false);
        F=(TextView) v.findViewById(R.id.FName);
        EditText N = (EditText) v.findViewById(R.id.Name);
        EditText D = (EditText) v.findViewById(R.id.GDay);
        EditText P = (EditText) v.findViewById(R.id.GPlace);
        EditText M = (EditText) v.findViewById(R.id.MyName);

        Button FaddB = (Button) v.findViewById(R.id.FaddB);
        FaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(N.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹명을 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(D.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"날짜를 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(P.getText().toString().equals("")) {
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(), "장소를 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(M.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"학급명을 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else {
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(), "그룹 아이디를 생성합니다", Toast.LENGTH_SHORT);
                    myToast.show();
                    F.setTextSize(Dimension.DP, 100);
                    F.setTextColor(Color.parseColor("#980000"));
                    F.setText("ABCDEF");//그룹 아이디 생성해야함
                    //올바르게 생성하였을 때
                    create=1;
                }
            }
        });

        Button GaddB = (Button) v.findViewById(R.id.GaddB);
        GaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create==1){
                    create=0;
                    //내용저장
                    Gname=N.getText().toString();
                    Gday=D.getText().toString();
                    Gplace=P.getText().toString();
                    Myname=M.getText().toString();
                    Fname=F.getText().toString();

                    //내용 전송 추가해야함


                    adapter Gadapter= new adapter();
                    Gadapter.items.add(new group(Gname,""));//그룹추가

                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹을 추가했습니다", Toast.LENGTH_SHORT);
                    myToast.show();
                    ((MainActivity)getActivity()).addGroup2();
                }else {
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹아이디를 생성해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}