package com.example.app1;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.app1.MainActivity.p_email;
import static com.example.app1.MainActivity.p_name;

public class JoinActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.219.107:3000";
    private EditText email;
    private EditText pass;
    private EditText passCheck;
    private EditText name;
    private RadioGroup gen;
    private Button join;
    private ImageView pro;
    private RadioButton teacher;
    private RadioButton parent;
    private LinearLayout email_check_Li;
    private EditText email_check;
    private Button email_check_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        passCheck = findViewById(R.id.passCheck);
        name = findViewById(R.id.name);
        gen = findViewById(R.id.gen);


        pro=findViewById(R.id.profile);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"프로필 사진 등록", Toast.LENGTH_SHORT);
                myToast.show();

                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });

        Button auth=findViewById(R.id.auth);
        email_check_Li=findViewById(R.id.email_check_Li);
        email_check=findViewById(R.id.email_check);
        email_check_Button=findViewById(R.id.email_check_button);

        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 인증메일 보내기
                email_check_Li.setVisibility(View.VISIBLE);

                HashMap<String, String> map = new HashMap<>();
                map.put("email", email.getText().toString());


                Call<CheckResult> call = retrofitInterface.executeCheck(map);


                call.enqueue(new Callback<CheckResult>() {
                    @Override
                    public void onResponse(Call<CheckResult> call, Response<CheckResult> response) {
                        if (response.code() == 200) {
                            CheckResult result = response.body();

                            email_check_Button.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View view){
                                    if((result.getChecking()).equals(email_check.getText().toString())) {
                                        Toast.makeText(JoinActivity.this, "인증이 완료되었습니다.", Toast.LENGTH_LONG).show();
                                        email_check_Button.setText("인증 완료");
                                    }
                                    else{
                                        Toast.makeText(JoinActivity.this, "인증번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        }
                        else if(response.code() == 404){
                            Toast.makeText(JoinActivity.this, "404 오류", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckResult> call, Throwable t) {
                        Toast.makeText(JoinActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        Button putimg=findViewById(R.id.putimg);
        putimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGalery();
            }
        });

        teacher = findViewById(R.id.teacher);
        parent = findViewById(R.id.parent);
        join = findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(pass.getText().toString().equals(passCheck.getText().toString()))){
                    Toast.makeText(JoinActivity.this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("email", email.getText().toString());
                    map.put("password", pass.getText().toString());
                    map.put("name", name.getText().toString());
                    if(teacher.isChecked()){
                        map.put("job", "선생님");
                    }
                    else{
                        map.put("job", "학부모");
                    }


                    Call<Void> call = retrofitInterface.executeSignup(map);
                    if(email_check_Button.getText().toString()=="인증 완료"){
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
                    else{
                        Toast.makeText(JoinActivity.this,
                                "이메일 인증이 완료되지 않았습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void toGalery(){
        Toast myToast = Toast.makeText(getApplicationContext(),"사진 등록", Toast.LENGTH_SHORT);
        myToast.show();
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
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




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                //ClipData clipData = data.getClipData();
                Uri fileUri = data.getData();
                ClipData clipData = data.getClipData();
                ArrayList<Uri> filePathList = new ArrayList<>();
                /*
                while(clipData.getItemCount()<6) {
                    Toast myToast = Toast.makeText(getApplicationContext(),"5장 이상 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                    toGalery();
                }*/
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri tempUri;
                    tempUri = clipData.getItemAt(i).getUri();
                    Log.i("temp: ", i + " " + tempUri.toString());
                    filePathList.add(tempUri);
                }
                ContentResolver resolver = this.getContentResolver();
                try {
                    /*
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imgBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), byteArrayOutputStream.toByteArray());
                    MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("postImg", p_name+".jpg" ,requestBody);
                    //imageView.setImageBitmap(imgBitmap);    // 선택한 이미지 이미지뷰에 셋
                    instream.close();   // 스트림 닫아주기
                    //saveBitmapToJpeg(imgBitmap);    // 내부 저장소에 저장*/

                    ArrayList<MultipartBody.Part> files = new ArrayList<>();


                    // 파일 경로들을 가지고있는 `ArrayList<Uri> filePathList`가 있다고 칩시다...
                    for (int i = 0; i < filePathList.size(); ++i) {
                        // Uri 타입의 파일경로를 가지는 RequestBody 객체 생성
                        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), String.valueOf(filePathList.get(i)));

                        // 사진 파일 이름
                        String fileName = "photo" + i + ".jpg";
                        // RequestBody로 Multipart.Part 객체 생성

                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("photo", fileName, fileBody);
                        Log.i("fileName", filePart.toString());
                        // 추가
                        files.add(filePart);
                    }

                    Call<ImageResult> call = retrofitInterface.Image(email.getText().toString(), files);


                    call.enqueue(new Callback<ImageResult>() {
                        @Override
                        public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                            if (response.code() == 200) {
                                Toast.makeText(JoinActivity.this, "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code() == 404){
                                Toast.makeText(JoinActivity.this, "404 오류", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ImageResult> call, Throwable t) {
                            Toast.makeText(JoinActivity.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(JoinActivity.this, "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri uri=data.getData();
                    Glide.with(getApplicationContext()).load(uri).into(pro);

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}

