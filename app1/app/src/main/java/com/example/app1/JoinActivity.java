package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText passCheck;
    private EditText name;
    private EditText birth;
    private RadioGroup gen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        passCheck=findViewById(R.id.passCheck);
        name=findViewById(R.id.name);
        birth=findViewById(R.id.birth);
        gen=findViewById(R.id.gen);

        ImageView pro=findViewById(R.id.profile);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"프로필 사진 등록", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        Button auth=findViewById(R.id.auth);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),email.getText()+"로 인증메일이 발송되었습니다.", Toast.LENGTH_SHORT);
                myToast.show();
                // 인증메일 보내기
            }
        });

        Button putimg=findViewById(R.id.putimg);
        putimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"사진 등록", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        Button join=findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast myToast = Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.", Toast.LENGTH_SHORT);
                myToast.show();

                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}
