package com.xrd.mutityperecycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xrd.mutityperecycler.R;
import com.xrd.mutityperecycler.bean.TeacherBean;
import com.xrd.mutityperecycler.bean.TeacherItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/9/17.
 */

public class DoctorAdapter extends Adapter {
    private Context mContext;
    private List<TeacherItemBean> datas = new ArrayList<>();
    private final LayoutInflater inflater;

    public DoctorAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<TeacherItemBean> list) {
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        final TeacherItemBean itemBean = datas.get(position);
        if (itemBean != null && itemBean.name != null)
            holder1.tvDoctorName.setText(itemBean.name);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, itemBean.name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_doctor_head)
        ImageView ivDoctorHead;
        @BindView(R.id.tv_doctor_name)
        TextView tvDoctorName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
