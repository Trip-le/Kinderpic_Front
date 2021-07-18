package com.example.app1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.FragmentPage1;
import com.example.app1.FragmentPage2;
import com.example.app1.FragmentPage3;
import com.example.app1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String tag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("상태","체크");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_2);


    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        Log.d("상태",tag);
        tag2=tag;
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                fragment = new FragmentPage1();
            } else if (id == R.id.navigation_2){
                fragment = new FragmentPage2();
            }else {
                fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
            Log.d("상태2",tag);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
        Log.d("상태","확인");

    }

    public void AddGroup() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("ag");
        if (fragment == null) {
            fragment=new AddGroup();
            fragmentTransaction.add(R.id.content_layout, fragment, "ag");
        } else {
            fragmentTransaction.remove(fragment);
            fragment=new AddGroup();
            fragmentTransaction.add(R.id.content_layout, fragment, "ag");
        }
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

    public void addGroup2() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag(tag2);
        if (fragment == null) {
            fragment=new FragmentPage2();
            fragmentTransaction.add(R.id.content_layout, fragment, tag2);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

    public void MainGroup(String name) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Bundle bundle=new Bundle();
        bundle.putString("name",name);


        Fragment fragment=fragmentManager.findFragmentByTag("mg");
        if (fragment == null) {
            fragment=new MainGroup();
            fragment.setArguments(bundle);
            fragmentTransaction.add(R.id.content_layout, fragment, "mg");
        } else {
            fragmentTransaction.remove(fragment);
            fragment=new MainGroup();
            fragment.setArguments(bundle);
            fragmentTransaction.add(R.id.content_layout, fragment, "mg");
        }



        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

    public void AddFriend() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("af");
        if (fragment == null) {
            fragment=new AddFriend();
            fragmentTransaction.add(R.id.content_layout, fragment, "af");
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

}