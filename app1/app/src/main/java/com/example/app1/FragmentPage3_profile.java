package com.example.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app1.R;

public class FragmentPage3_profile extends Fragment {
    ImageView profile_mod;
    ImageView logout;
    TextView leave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment3_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        leavedia.show();
    }

   }