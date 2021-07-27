package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private String BASE_URL = "http://192.168.114.1:3000";
    private EditText logid;
    private EditText logpass;
    private Button login;
    private Button join;
    private TextView find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        login = findViewById(R.id.login);
        join = findViewById(R.id.join);
        find = findViewById(R.id.find);

        // 로그인 버튼 클릭 시
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        // 회원가입 버튼 클릭 시
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        // 아이디/비번 찾기 클릭 시
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"찾기", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }

    private void changeMain() { //메인으로
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void handleLogin(){
        logid = findViewById(R.id.logid); // id 입력 텍스트 가져오기
        logpass = findViewById(R.id.logpass); // pw 입력 텍스트 가져오기

        // HashMap에 로그인 정보 저장
        HashMap<String, String> map = new HashMap<>();

        map.put("email", logid.getText().toString());
        map.put("password", logpass.getText().toString());

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
                }
                else if(response.code() == 404){
                    Toast.makeText(LoginActivity.this, "404 오류", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}