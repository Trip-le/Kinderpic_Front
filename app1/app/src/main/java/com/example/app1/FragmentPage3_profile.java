package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app1.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPage3_profile extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.35.105:3000";
    ImageView profile_mod;
    ImageView logout;
    TextView leave;
    ImageButton back;
    TextView frag3_email;
    TextView email;
    TextView name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment3_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = view.findViewById(R.id.email_pr_tv);
        name = view.findViewById(R.id.name_pr_tv);

        email.setText(MainActivity.p_email);
        name.setText(MainActivity.p_name);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toSetting();
            }
        });

        profile_mod = view.findViewById(R.id.profile_mod);
        profile_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).frag3_profile_modi();
            }
        });

        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog1();
            }
        });

        leave = view.findViewById(R.id.leave);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog2();
            }
        });
    }

    private void changeMain() { //메인으로
        Intent intent = new Intent(getView().getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void showAlertDialog1() {
        AlertDialog.Builder logoutdia = new AlertDialog.Builder(getView().getContext());
        View dialogView = View.inflate(getView().getContext(), R.layout.dialog_logout, null);
        logoutdia.setView(dialogView);
        logoutdia.show();


    }

    private void showAlertDialog2() {
        AlertDialog.Builder leavedia = new AlertDialog.Builder(getView().getContext());

        View dialogView = View.inflate(getView().getContext(), R.layout.dialog_leave, null);
        leavedia.setView(dialogView);
        AlertDialog alertDialog = leavedia.create();


        TextView ok_leave = dialogView.findViewById(R.id.ok_leave);
        TextView cancel_leave = dialogView.findViewById(R.id.cancel_leave);

        ok_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HashMap에 로그인 정보 저장
                HashMap<String, String> map = new HashMap<>();

                //*아이디랑 비밀번호 들고와서 탈퇴할 때 보내기 -고은
                map.put("email", MainActivity.p_email);
                map.put("password", MainActivity.p_password);


                Call<LoginResult> call = retrofitInterface.executeLeave(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getView().getContext(), "탈퇴 완료", Toast.LENGTH_LONG).show();
                            changeMain();
                        }
                        else if(response.code() == 404){
                            Toast.makeText(getView().getContext(), "404 오류", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(getView().getContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        cancel_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();



    }

}