package com.xrd.mutityperecycler.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2018/9/14.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder mUnBinder;
    protected Activity mActivity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        this.mContext=context;
        this.mActivity= (Activity) context;
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), container, false);
//        View inflate = View.inflate(container.getContext(), getLayoutId(), container);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        initView();
        initDataAndEnvnt();

    }

    public abstract void initDataAndEnvnt();
    public abstract void initView();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
