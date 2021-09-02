package com.example.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private String BASE_URL = "http://192.168.0.3:3000";
    private EditText logid;
    private EditText logpass;
    private Button login;
    private Button join;
    private TextView find;
    private String intent_email;
    private String intent_password;
    private String intent_name;

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
                //changeMain();
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
        //액티비티 스택제거
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Bundle bundle = new Bundle();

        bundle.putString("email", intent_email);
        bundle.putString("password", intent_password);
        bundle.putString("name", intent_name);
        intent.putExtras(bundle);

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

                    intent_email = result.getEmail();
                    intent_password = result.getPassword();
                    intent_name = result.getName();

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

    //키보드 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}