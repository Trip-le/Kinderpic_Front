package com.example.app1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddGroup extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.0.3:3000";
    String Gname;
    String Gday;
    String Gplace;
    String Myname;
    String Fname;
    TextView F;
    private int create=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.add_group,container,false);
        F=(TextView) v.findViewById(R.id.FName);
        EditText N = (EditText) v.findViewById(R.id.Name);
        EditText D = (EditText) v.findViewById(R.id.GDay);
        EditText P = (EditText) v.findViewById(R.id.GPlace);
        EditText M = (EditText) v.findViewById(R.id.MyName);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Button FaddB = (Button) v.findViewById(R.id.FaddB);
        FaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(N.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹명을 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(D.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"날짜를 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(P.getText().toString().equals("")) {
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(), "장소를 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else if(M.getText().toString().equals("")){
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"학급명을 입력해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else {

                    Call<String> call = retrofitInterface.groupId();

                    call.enqueue(new Callback<String>() {
                        @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if (response.code() == 200) {
                                        String groupId=response.body();
                                        F.setTextSize(Dimension.DP, 100);
                                        F.setTextColor(Color.parseColor("#980000"));
                                        F.setText(groupId);
                                        create=1;
                                        Toast myToast = Toast.makeText(getActivity().getApplicationContext(), "그룹 아이디를 생성합니다", Toast.LENGTH_SHORT);
                                myToast.show();

                            } else if (response.code() == 400) {
                                Toast.makeText(getView().getContext(), "404 오류", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getView().getContext(), t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        Button GaddB = (Button) v.findViewById(R.id.GaddB);
        GaddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create==1){
                    create=0;
                    //내용저장
                    Gname=N.getText().toString();
                    Gday=D.getText().toString();
                    Gplace=P.getText().toString();
                    Myname=M.getText().toString();
                    Fname=F.getText().toString();

                    //내용 전송 추가해야함


                    adapter Gadapter= new adapter();
                    Gadapter.items.add(new group(Gname,""));//그룹추가

                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹을 추가했습니다", Toast.LENGTH_SHORT);
                    myToast.show();
                    ((MainActivity)getActivity()).addGroup2();
                }else {
                    Toast myToast = Toast.makeText(getActivity().getApplicationContext(),"그룹아이디를 생성해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }
            }
        });

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}