package com.example.app1;

import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://172.30.1.25:3000";

    private EditText emailEdt;
    private EditText passwordEdt;
=======
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

>>>>>>> one/main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

<<<<<<< HEAD
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                handleSignup();
            }
        });
    }

    private void changeMain() {
        Intent intent = new Intent(this, MainActivity.class); // 두번째 인자에 이동할 액티비티

        startActivityForResult(intent, 0);
    }

    private void handleLogin(){
        emailEdt = findViewById(R.id.id_first);
        passwordEdt = findViewById(R.id.pw_first);

        HashMap<String, String> map = new HashMap<>();

        map.put("email", emailEdt.getText().toString());
        map.put("password", passwordEdt.getText().toString());

        Call<LoginResult> call = retrofitInterface.executeLogin(map);

        call.enqueue(new Callback<LoginResult>() {

            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200) {

                    LoginResult result = response.body();

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                    builder1.setTitle(result.getName());
                    builder1.setMessage(result.getEmail());

                    builder1.show();

                    changeMain();

                } else if(response.code()==404){
                    Toast.makeText(LoginActivity.this, "404 에러 페이지",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
=======
        Button login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button join=findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        TextView find=findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"찾기", Toast.LENGTH_SHORT);
                myToast.show();
>>>>>>> one/main
            }
        });
    }

<<<<<<< HEAD
    private void handleSignup() {
        Intent intent = new Intent(this, JoinActivity.class); // 두번째 인자에 이동할 액티비티

        startActivityForResult(intent, 0);
    }

=======
>>>>>>> one/main
}
