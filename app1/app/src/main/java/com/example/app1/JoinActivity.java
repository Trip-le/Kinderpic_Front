package com.example.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
<<<<<<< HEAD
=======

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.30.1.25:3000";
    private EditText email;
    private EditText pass;
    private EditText passCheck;
    private EditText name;
    private EditText birth;
    private RadioGroup gen;
>>>>>>> one/main

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
<<<<<<< HEAD
=======

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        passCheck=findViewById(R.id.passCheck);
        name=findViewById(R.id.name);
        birth=findViewById(R.id.birth);
        gen=findViewById(R.id.gen);
>>>>>>> one/main

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        signupBtn = findViewById(R.id.signupBtn);
        emailEdt = findViewById(R.id._email);
        nameEdt = findViewById(R.id._name);
        passwordEdt = findViewById(R.id._password);
        birthEdt = findViewById(R.id._birth);
        signupBtn = findViewById(R.id.signupBtn);

        // 회원 가입 등록 버튼 클릭 시
        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

<<<<<<< HEAD
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
        Button join=findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(pass.getText().toString().equals(passCheck.getText().toString()))){
                    Toast.makeText(JoinActivity.this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", email.getText().toString());
                    map.put("password", pass.getText().toString());
                    map.put("name", name.getText().toString());
                    map.put("birth", birth.getText().toString());

                    Call<Void> call = retrofitInterface.executeSignup(map);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Toast.makeText(JoinActivity.this,
                                        "회원가입이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else if (response.code() == 400) {
                                Toast.makeText(JoinActivity.this, "이미 가입된 정보입니다.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(JoinActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
>>>>>>> one/main
            }
        });

    }
}
