package com.example.app1;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class show_img_dialog extends Dialog {
    private Context context;
    private DialogClickListener dialogClickListener;
    private Activity mainActivity;
    private Button ok, no;
    private RecyclerView recyclerView;
    private show_img_adapter adapter;
    private GridLayoutManager GridLayoutManager;
    private ArrayList<Uri> uriList;

    public show_img_dialog(@NonNull Context context, Activity mainActivity, ArrayList<Uri> uriList, DialogClickListener dialogClickListener) {
        super(context);
        this.context = context;
        this.mainActivity = mainActivity;
        this.uriList = uriList;
        this.dialogClickListener = dialogClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_img_list);

        ok = findViewById(R.id.Yes);
        no = findViewById(R.id.No);
        recyclerView = findViewById(R.id.show_img_recyclerView);

        adapter = new show_img_adapter(uriList, context);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        GridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(GridLayoutManager);

        ok.setOnClickListener(v -> {
            //Intent intent = new Intent();
            //intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            //intent.setAction(Intent.ACTION_GET_CONTENT);
            //mainActivity.startActivityForResult(intent, 101);
            this.dialogClickListener.onPositiveClick();
            dismiss();
        });
        no.setOnClickListener(v -> {
            this.dialogClickListener.onNegativeClick();
            dismiss();
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //권한을 허용 했을 경우
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "권한 허용 : " + permissions[i]);
                }
            }
        }
    }

    public void checkSelfPermission() {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }

        if (TextUtils.isEmpty(temp) == false) { // 권한 요청
            ActivityCompat.requestPermissions(mainActivity, temp.trim().split(" "), 1);
        } else { // 모두 허용 상태
            Toast.makeText(context, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 101 && resultCode == RESULT_OK) {
            try {
                //InputStream is = MainActivity.getContentResolver().openInputStream(data.getData());
                //Bitmap bm = BitmapFactory.decodeStream(is);
                // is.close();
                //
                this.dialogClickListener.onPositiveClick();
                dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 101 && resultCode == RESULT_CANCELED) {
            Toast.makeText(context, "취소", Toast.LENGTH_SHORT).show();
        }
    }
}
