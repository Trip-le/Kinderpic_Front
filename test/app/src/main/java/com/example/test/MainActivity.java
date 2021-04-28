package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FROM_MULTI_ALBUM = 1;
    private Button B;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B=(Button)findViewById(R.id.button);

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoAlbum();
            }
        });

    }

    public void GoAlbum(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_FROM_MULTI_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_FROM_MULTI_ALBUM:

                if (resultCode == Activity.RESULT_OK) {
                    ArrayList imageList = new ArrayList<>();

                    // 멀티 선택을 지원하지 않는 기기에서는 getClipdata()가 없음 => getData()로 접근해야 함
                    if (data.getClipData() == null) {

                        imageList.add(String.valueOf(data.getData()));
                    } else {

                        ClipData clipData = data.getClipData();
                        if (clipData.getItemCount() > 10){
                            Toast.makeText(MainActivity.this, "사진은 10개까지 선택가능 합니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // 멀티 선택에서 하나만 선택했을 경우
                        else{
                            for (int i = 0; i < clipData.getItemCount(); i++) {
                                imageList.add(clipData.getItemAt(i).getUri());
                                GridView gridViewImages = (GridView)findViewById(R.id.gridViewImages);
                                ImageGridAdapter imageGridAdapter = new ImageGridAdapter(this, imageList);
                                gridViewImages.setAdapter(imageGridAdapter);
                            }
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "사진 선택을 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public class ImageGridAdapter extends BaseAdapter {

        Context context = null;

        //-----------------------------------------------------------
        // imageIDs는 이미지 파일들의 리소스 ID들을 담는 배열입니다.
        // 이 배열의 원소들은 자식 뷰들인 ImageView 뷰들이 무엇을 보여주는지를
        // 결정하는데 활용될 것입니다.

        ArrayList<Uri> imageIDs = null;

        public ImageGridAdapter(Context context, ArrayList imageIDs) {
            this.context = context;
            this.imageIDs = imageIDs;
        }


        public int getCount() {
            return (null != imageIDs) ? imageIDs.size() : 0;
        }



        public Object getItem(int position) {
            return (null != imageIDs) ? imageIDs.get(position) : 0;
        }



        public long getItemId(int position) {
            return position;
        }



        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;

            if (null != convertView)
                imageView = (ImageView)convertView;
            else {
                //---------------------------------------------------------------
                // GridView 뷰를 구성할 ImageView 뷰의 비트맵을 정의합니다.
                // 그리고 그것의 크기를 320*240으로 줄입니다.
                // 크기를 줄이는 이유는 메모리 부족 문제를 막을 수 있기 때문입니다.
                Uri ur=imageIDs.get(position);
                Bitmap bmp
                        = null;
                try {
                    bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), ur);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);

                //---------------------------------------------------------------
                // GridView 뷰를 구성할 ImageView 뷰들을 정의합니다.
                // 뷰에 지정할 이미지는 앞에서 정의한 비트맵 객체입니다.

                imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);

                imageView.setImageBitmap(bmp);
            }

            return imageView;
        }

    }

}