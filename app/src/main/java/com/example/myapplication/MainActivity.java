package com.example.myapplication;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.fragment.Fragment1;
import com.example.myapplication.fragment.Fragment2;
import com.example.myapplication.fragment.Fragment3;
import com.example.myapplication.fragment.MainFragment;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentTransaction ft;
    private MainFragment mainFragment;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private ArrayList<Fragment> fragments;
    private LinearLayout ll_1,ll_2,ll_3,ll_4;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        ll_1 = (LinearLayout) findViewById(R.id.ll_1);
        ll_2 = (LinearLayout) findViewById(R.id.ll_2);
        ll_3 = (LinearLayout) findViewById(R.id.ll_3);
        ll_4 = (LinearLayout) findViewById(R.id.ll_4);
        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);
        ll_3.setOnClickListener(this);
        ll_4.setOnClickListener(this);


        fragments =new ArrayList<>();
        mainFragment = new MainFragment();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragments.add(mainFragment);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);


        android.app.FragmentManager fragmentManager = getFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame1, fragments.get(1));
        ft.replace(R.id.frame1, fragments.get(0));
        ft.commit();
    }
    @Override
    public void onClick(View v) {
        ll_1.setSelected(false);
        ll_2.setSelected(false);
        ll_3.setSelected(false);
        ll_4.setSelected(false);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        ft = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.ll_1:
                ll_1.setSelected(true);
                ft.replace(R.id.frame1, fragments.get(0));
                ft.show(fragments.get(0));
                ft.commit();

                break;
            case R.id.ll_2:
                ll_2.setSelected(true);
                ft.replace(R.id.frame1, fragments.get(1));
                ft.show(fragments.get(1));
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.ll_3:
                ll_3.setSelected(true);
                ft.replace(R.id.frame1, fragments.get(2));
                ft.show(fragments.get(2));
                ft.commit();
                break;
            case R.id.ll_4:
                ll_4.setSelected(true);
                ft.replace(R.id.frame1, fragments.get(3));
                ft.show(fragments.get(3));
                ft.show(fragments.get(3));
                ft.commit();
                break;
            default:
                break;
        }
    }
    private long timeExit=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-timeExit>2000){
                timeExit=System.currentTimeMillis();
                Toast.makeText(getApplicationContext(),"再按一下退出",Toast.LENGTH_SHORT).show();
            }
            else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
