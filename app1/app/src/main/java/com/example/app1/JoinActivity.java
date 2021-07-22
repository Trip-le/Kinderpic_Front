package com.example.app1;

<<<<<<< HEAD
import android.graphics.Paint;
=======
import android.content.Intent;
>>>>>>> one/main
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
=======
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
>>>>>>> one/main
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.30.1.25:3000";
    private EditText emailEdt;
    private EditText nameEdt;
    private EditText birthEdt;
    private EditText passwordEdt;
    private Button signupBtn;
=======
public class JoinActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private EditText passCheck;
    private EditText name;
    private EditText birth;
    private RadioGroup gen;


>>>>>>> one/main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

<<<<<<< HEAD
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        signupBtn = findViewById(R.id.signupBtn);
        emailEdt = findViewById(R.id._email);
        nameEdt = findViewById(R.id._name);
        passwordEdt = findViewById(R.id._password);
        birthEdt = findViewById(R.id._birth);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", emailEdt.getText().toString());
                map.put("password", passwordEdt.getText().toString());
                map.put("name", nameEdt.getText().toString());
                map.put("birth", birthEdt.getText().toString());

                Call<Void> call = retrofitInterface.executeSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200) {
                            Toast.makeText(JoinActivity.this,
                                    "Signed up succesfully", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400){
                            Toast.makeText(JoinActivity.this, "Already registered",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(JoinActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
=======
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
>>>>>>> one/main
            }
        });

    }
<<<<<<< HEAD
=======

>>>>>>> one/main
}
