package com.xrd.mutityperecycler.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2018/9/14.
 */

public class MainAdapter extends PagerAdapter {
    private String[] arr;

    public void setArr(String[] arr) {
        this.arr = arr;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(arr!=null&&arr.length>0){
            return arr[position];
        }
        return super.getPageTitle(position);
    }
}
