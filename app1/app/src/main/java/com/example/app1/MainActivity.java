package com.example.app1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
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
    String tag1, tag2, tag3;
    private long backKeyPressedTime = 0;
    public static String p_email;
    public static String p_password;
    public static String p_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        p_email = bundle.getString("email");
        p_name = bundle.getString("name");
        p_password = bundle.getString("password");

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

        remove("ag");
        remove("mg");
        remove("frag3_profile");
        remove("frag3_qna");
        remove("frag3_profile_modi");
        remove("frag3_mannual");

        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment == null) {
            if (id == R.id.navigation_1) {
                tag1=tag;
                fragment = new FragmentPage1();
            } else if (id == R.id.navigation_2){
                tag2=tag;
                fragment = new FragmentPage2();
            }else {
                tag3=tag;
                fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

    //그룹만들기로
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
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }

    //메인으로
    public void addGroup2() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        remove("ag");
        remove("mg");
        remove("frag3_profile");
        remove("frag3_qna");
        remove("frag3_profile_modi");
        remove("frag3_mannual");

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

    //설정으로
    public void toSetting() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        remove("ag");
        remove("mg");
        remove("frag3_profile");
        remove("frag3_qna");
        remove("frag3_profile_modi");
        remove("frag3_mannual");

        Fragment fragment=fragmentManager.findFragmentByTag(tag3);
        if (fragment == null) {
            fragment=new FragmentPage3();
            fragmentTransaction.add(R.id.content_layout, fragment, tag3);
        } else {
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();

    }

    //그룹으로
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
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }

    //내정보로
    public void frag3_profile() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("frag3_profile");

        if (fragment == null) {
            fragment=new FragmentPage3_profile();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_profile");
        } else {
            fragmentTransaction.remove(fragment);
            fragment = new FragmentPage3_profile();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_profile");
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }

    //자주묻는 질문으로
    public void qna() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("frag3_qna");

        if (fragment == null) {
            fragment=new FragmentPage3_FAQ();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_qna");
        } else {
            fragmentTransaction.remove(fragment);
            fragment = new FragmentPage3_FAQ();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_qna");
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }

    //메뉴얼로
    public void mannual() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("frag3_mannual");

        if (fragment == null) {
            fragment=new mannual();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_mannual");
        } else {
            fragmentTransaction.remove(fragment);
            fragment = new mannual();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_mannual");
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }

    //회원정보수정으로
    public void frag3_profile_modi() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag("frag3_profile_modi");
        if (fragment == null) {
            fragment=new FragmentPage3_profile_modi();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_profile_modi");
        } else {
            fragmentTransaction.remove(fragment);
            fragment = new FragmentPage3_profile_modi();
            fragmentTransaction.add(R.id.content_layout, fragment, "frag3_profile_modi");
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.addToBackStack(null);//뒤로가기 눌렀을 떄 이전 프래그먼트로 이동가능
        fragmentTransaction.commit();

    }


    //프래그먼트 트랜젝션 삭제
    public void remove(String tag){
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            Log.d("체크","체크1");
        }
    }


    //두번 눌러 종료
    @Override
    public void onBackPressed(){

        Toast t = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                t.show();
            } else {
                t.cancel();
                finish();
            }
        } else {
            super.onBackPressed();
        }

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