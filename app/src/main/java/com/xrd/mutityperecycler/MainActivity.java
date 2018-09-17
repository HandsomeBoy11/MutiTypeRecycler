package com.xrd.mutityperecycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.xrd.mutityperecycler.adapter.MainAdapter;
import com.xrd.mutityperecycler.adapter.MyPagerAdapter;
import com.xrd.mutityperecycler.fragment.FirstFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    SmartTabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> fragments;
    private MyPagerAdapter myPagerAdapter;
    private String[] tabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getabName();
        initView();
        initFragments();
    }

    private void getabName() {
        tabNames = getResources().getStringArray(R.array.tab_name);

    }

    private void initFragments() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabNames.length; i++) {
            FirstFragment firstFragment = new FirstFragment();
            fragments.add(firstFragment);
        }
        myPagerAdapter.setFragment(fragments);
    }

    private void initView() {
//        MainAdapter myPagerAdapter=new MainAdapter();
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.setArr(tabNames);
        vp.setAdapter(myPagerAdapter);
        tablayout.setViewPager(vp);
    }


}
