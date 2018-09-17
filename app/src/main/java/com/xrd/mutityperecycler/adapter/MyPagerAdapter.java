package com.xrd.mutityperecycler.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xrd.mutityperecycler.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/9/14.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private String[] arr;
    private List<Fragment> mFragmetns=new ArrayList<>();

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmetns.get(position);
    }

    @Override
    public int getCount() {
        return mFragmetns.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(arr!=null&&arr.length>0){
            return arr[position];
        }
        return super.getPageTitle(position);
    }

    public void setFragment(List<Fragment> fragment) {
        mFragmetns.clear();
        mFragmetns.addAll(fragment);
        notifyDataSetChanged();
    }
}
