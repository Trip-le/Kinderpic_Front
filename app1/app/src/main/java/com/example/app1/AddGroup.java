package com.example.app1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String SeletedItemsString;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.add_group,container,false);
        F=(TextView) v.findViewById(R.id.FName);

        Button FaddB = (Button) v.findViewById(R.id.FaddB);
        FaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹멤버 추가 버튼", Toast.LENGTH_SHORT);
                myToast.show();
                Dialog();

            }
        });

        Button GaddB = (Button) v.findViewById(R.id.GaddB);
        GaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내용저장
                EditText N = (EditText) v.findViewById(R.id.Name);
                Gname=N.getText().toString();
                EditText D = (EditText) v.findViewById(R.id.GDay);
                Gday=D.getText().toString();
                EditText P = (EditText) v.findViewById(R.id.GPlace);
                Gplace=P.getText().toString();
                EditText M = (EditText) v.findViewById(R.id.MyName);
                Myname=M.getText().toString();

                Fname=F.getText().toString();
                //내용 전송 추가해야함

                adapter Gadapter= new adapter();
                Gadapter.items.add(new group(Gname,""));

                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹을 추가했습니다.", Toast.LENGTH_SHORT);
                myToast.show();
                ((MainActivity)getActivity()).addGroup2();
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void Dialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        ArrayList<String> selectedItems = new ArrayList<String>();
        ArrayList<String> Items=new ArrayList<String>();
        Items.add("a");
        //String[] items = getResources().getStringArray();
        String[] items = {"a","b"};
        builder.setTitle("친구 리스트");

        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos, boolean isChecked)
            {
                if(isChecked == true) // Checked 상태일 때 추가
                {
                    selectedItems.add(items[pos]);
                }
                else				  // Check 해제 되었을 때 제거
                {
                    selectedItems.remove(pos);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos)
            {
                SeletedItemsString = "";

                for(int i =0; i<selectedItems.size();i++)
                {
                    SeletedItemsString =  SeletedItemsString + " " + selectedItems.get(i);
                }
                if(SeletedItemsString.equals("")){
                    Toast toast = Toast.makeText(getContext(), "아무것도 선택되지 않았습니다.",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    F.setText(SeletedItemsString);
                    Toast toast = Toast.makeText(getContext(), "선택 된 항목은 :" + SeletedItemsString,Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
        builder.setNegativeButton("취소",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos){

            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}