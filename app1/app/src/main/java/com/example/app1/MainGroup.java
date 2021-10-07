package com.example.app1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;
import static com.example.app1.MainActivity.p_email;
import static com.example.app1.MainActivity.p_name;
import static com.example.app1.show_img_adapter.suc;

public class MainGroup extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://13.209.5.100:3000";
    private GridLayoutManager GridLayoutManager;
    private GroupAdapter Gadapter;
    private FirebaseVisionFaceDetectorOptions highAccuracyOpts;
    private ArrayList<Uri> uriList2 = new ArrayList<>();//기준치 이상 이미지 리스트
    Dialog Dinfo;
    Dialog Dname;
    String GName;
    show_img_dialog show_img_dialog;
    CheckTypesTask task;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //얼굴 표정 분류 객체
        highAccuracyOpts =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();


        Gson gson=new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.maingroup, container, false);

        Bundle bundle=getArguments();
        GName=bundle.getString("name");
        TextView Gname=(TextView)v.findViewById(R.id.Gname);
        Gname.setText(GName);


        Dinfo=new Dialog(container.getContext());
        Dinfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dinfo.setContentView(R.layout.grouppop);

        ImageView info=(ImageView)v.findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showinfo();
            }
        });

        Dname=new Dialog(container.getContext());
        Dname.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Dname.setContentView(R.layout.groupname);

        Gname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showinfo2();
            }
        });

        ImageButton back = (ImageButton) v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).addGroup2();
            }
        });

        FloatingActionButton down = (FloatingActionButton) v.findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"다운로드", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        FloatingActionButton up = (FloatingActionButton) v.findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"업로드", Toast.LENGTH_SHORT);
                myToast.show();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 101);

            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.grecyclerView);
        GridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(GridLayoutManager);
        Gadapter= new GroupAdapter();
        GroupAdapter.items.clear();
        GroupAdapter.items.add(new folder("name1","","","",""));
        GroupAdapter.items.add(new folder("name2","","","",""));
        GroupAdapter.items.add(new folder("name3","","","",""));
        recyclerView.setAdapter(Gadapter);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void showinfo(){
        Dinfo.show(); // 다이얼로그 띄우기
        TextView tv1=Dinfo.findViewById(R.id.groupn);
        tv1.setText("그룹명: "+GName);
        TextView tv2=Dinfo.findViewById(R.id.groupl);
        TextView tv3=Dinfo.findViewById(R.id.groupd);
        TextView tv4=Dinfo.findViewById(R.id.groupf);

        HashMap<String, String> map = new HashMap<>();

        map.put("email", MainActivity.p_email);
        map.put("groupname", GName);

        Call<groupResult> call = retrofitInterface.showGroup(map);

        call.enqueue(new Callback<groupResult>() {
            @Override
            public void onResponse(Call<groupResult> call, Response<groupResult> response) {
                Toast.makeText(getContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {

                    groupResult result = response.body();

                    tv2.setText("위치: "+result.getGroup_place());
                    tv3.setText("날짜: "+result.getGroup_date());
                    tv4.setText("학교 학급명: "+result.getGroup_schoolinfo());

                }
                else if(response.code() == 400){
                    Toast.makeText(getView().getContext(), "불러오기 오류", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<groupResult> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });



        Dinfo.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dinfo.dismiss();
            }
        });
    }



    public void showinfo2(){
        Dname.show(); // 다이얼로그 띄우기
        TextView names=Dname.findViewById(R.id.namearr);
        names.setText(names.getText()+" "+"a b c d");

        Dname.findViewById(R.id.ok2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dname.dismiss();
            }
        });
    }


    private ArrayList<Bitmap> fileList = new ArrayList<>();
    private ArrayList<Uri> uriList = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                ClipData clipData = data.getClipData();

                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri tempUri;
                    tempUri = clipData.getItemAt(i).getUri();
                    uriList.add(tempUri);

                    ContentResolver resolver = getActivity().getContentResolver();

                    InputStream instream = null;
                    try {
                        instream = resolver.openInputStream(tempUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    fileList.add(imgBitmap);
                }


                task = new CheckTypesTask();
                task.execute();


/*
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    imgBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray());
                    MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("postImg", p_name+".jpg" ,requestBody);
                    //imageView.setImageBitmap(imgBitmap);    // 선택한 이미지 이미지뷰에 셋
                    instream.close();   // 스트림 닫아주기
                    //saveBitmapToJpeg(imgBitmap);    // 내부 저장소에 저장

                    Call<ImageResult> call = retrofitInterface.Image(p_email, uploadFile);



                    call.enqueue(new Callback<ImageResult>() {
                        @Override
                        public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                            if (response.code() == 200) {
                                Toast.makeText(getContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code() == 404){
                                Toast.makeText(getContext(), "404 오류", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ImageResult> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            */
            }
        }
    }

    private int flag=0;
    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(getContext());

        @Override
        public void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("사진업로드중입니다..");

            //show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        public Void doInBackground(Void... voids) { //지정된 함수 실행
            for(int i=0; i<fileList.size();i++){
                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(fileList.get(i));
                FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                        .getVisionFaceDetector(highAccuracyOpts);

                int finalI = i;//현재 이미지 번호

                Task<List<FirebaseVisionFace>> result =
                        detector.detectInImage(image)
                                .addOnSuccessListener(
                                        new OnSuccessListener<List<FirebaseVisionFace>>() {
                                            @Override
                                            public void onSuccess(List<FirebaseVisionFace> faces) {
                                                for (FirebaseVisionFace face : faces) {
                                                    if (face.getSmilingProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                                        float smileProb = face.getSmilingProbability();
                                                        ((MainActivity)getActivity()).getlog(Float.toString(smileProb));
                                                        if(smileProb>0.5){
                                                            uriList2.add(uriList.get(finalI));
                                                            if(finalI==fileList.size()-1){
                                                                flag=1;
                                                                ((MainActivity)getActivity()).getlog(Integer.toString(flag));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        })
                                .addOnFailureListener(
                                        new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Task failed with an exception
                                                // ...
                                            }
                                        });

            }


                try {
                    while(flag!=1){
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            return null;
        }

        @Override
        public void onPostExecute(Void aVoid) {//진행도 max값 지정
            asyncDialog.dismiss();
            super.onPostExecute(aVoid);

            show_img_dialog=new show_img_dialog(getContext(), (MainActivity) getActivity(), uriList2, new DialogClickListener() {
                @Override
                public void onPositiveClick() {
                    Toast.makeText(getContext(),"이미지 전송",Toast.LENGTH_SHORT).show();

                    try{

                    ArrayList<MultipartBody.Part> files = new ArrayList<>();
                    HashMap<String, RequestBody> map = new HashMap<>();


                    for (int i = 0; i < uriList2.size(); ++i) {
                        // Uri 타입의 파일경로를 가지는 RequestBody 객체 생성
                        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), String.valueOf(uriList2.get(i)));
                        // 사진 파일 이름
                        String fileName = p_name + i + ".jpg";
                        // RequestBody로 Multipart.Part 객체 생성

                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("photo", fileName, fileBody);
                        Log.i("fileName", filePart.toString());
                        // 추가
                        files.add(filePart);
                    }

                    RequestBody email = RequestBody.create(MediaType.parse("text/plain"), p_email);
                    map.put("email", email);

                    String token = p_email;
                    Call<Void> call = retrofitInterface.EmotionImage(token, files, map);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Toast.makeText(getContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.code() == 404){
                                Toast.makeText(getContext(), "404 오류", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                }

                }

                @Override
                public void onNegativeClick() {
                    Toast.makeText(getContext(),"업로드가 취소되었습니다.",Toast.LENGTH_SHORT).show();
                }
            });
            show_img_dialog.setCanceledOnTouchOutside(false);//다이얼로그 외부 터치시 꺼짐
            show_img_dialog.setCancelable(true);//뒤로가기 버튼으로 취소
            show_img_dialog.show();
        }
    }

/*
    public void saveBitmapToJpeg(Bitmap bitmap) {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getCacheDir(), imgName);    // 파일 경로와 이름 넣기
        try {
            tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
            out.close();    // 스트림 닫아주기
            Toast.makeText(getContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }*/
}



