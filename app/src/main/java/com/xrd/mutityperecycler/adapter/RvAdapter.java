package com.xrd.mutityperecycler.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xrd.mutityperecycler.R;
import com.xrd.mutityperecycler.bean.BannerBean;
import com.xrd.mutityperecycler.bean.LineBean;
import com.xrd.mutityperecycler.bean.NomalBean;
import com.xrd.mutityperecycler.bean.NomalSub2Bean;
import com.xrd.mutityperecycler.bean.TeacherBean;
import com.xrd.mutityperecycler.bean.TitleBean;
import com.xrd.mutityperecycler.utils.GlideImageLoader;
import com.xrd.mutityperecycler.utils.SpaceItemDecoration;
import com.xrd.mutityperecycler.utils.UIUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2018/9/14.
 */

public class RvAdapter extends RecyclerView.Adapter {
    public static final int BANNER = 0x01;
    public static final int TEACHER = 0x02;
    public static final int TITLE = 0x03;
    public static final int NOMAL = 0x04;
    public static final int LINE=0x05;
    private List<Object> mList = new ArrayList<>();
    private Context mContext;
    private final LayoutInflater inflater;

    public RvAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType) {
            case BANNER:
                view=inflater.inflate(R.layout.main_banner,parent,false);
                break;
            case TEACHER:
                view=inflater.inflate(R.layout.main_teacher,parent,false);
                break;
            case TITLE:
                view=inflater.inflate(R.layout.main_title,parent,false);
                break;
            case NOMAL:
                view=inflater.inflate(R.layout.main_nomal,parent,false);
                break;
            case LINE:
                view=inflater.inflate(R.layout.line10,parent,false);
                break;

        }
        return new ViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = mList.get(position);
        int type = getItemViewType(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        switch (type){
            case BANNER:
                BannerBean bannerBean = (BannerBean) o;
                setBanner(viewHolder,bannerBean);
                break;
            case TEACHER:
                TeacherBean teacherBean = (TeacherBean) o;
                setTeacher(viewHolder,teacherBean);
                break;
            case TITLE:
                TitleBean titleBean = (TitleBean) o;
                setTitle(viewHolder,titleBean);
                break;
            case NOMAL:
                NomalSub2Bean nomalBean = (NomalSub2Bean) o;
                setNomal(viewHolder,nomalBean);
                break;
            case LINE:
                LineBean lineBean = (LineBean) o;
                break;
        }

    }

    /**
     * 设置普通列表
     * @param viewHolder
     * @param nomalBean
     */
    private void setNomal(ViewHolder viewHolder, final NomalSub2Bean nomalBean) {
        TextView tvAddress = viewHolder.tvAddress;
        TextView tvTitle = viewHolder.tvTitle;
        tvTitle.setText(nomalBean.content);
        tvAddress.setText(nomalBean.address);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, nomalBean.content, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置标题
     * @param viewHolder
     * @param titleBean
     */
    private void setTitle(ViewHolder viewHolder, final TitleBean titleBean) {
        Log.e("TitleBean",titleBean+"");
        TextView title = viewHolder.tvTeacher;
        ImageView ivMore = viewHolder.ivMore;
        if(titleBean!=null&&titleBean.titleName!=null){
            title.setText(titleBean.titleName);
        }
        if(titleBean.isMore){
            ivMore.setVisibility(View.VISIBLE);
        }else{
            ivMore.setVisibility(View.GONE);
        }
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, titleBean.titleName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 推荐名师
     * @param viewHolder
     * @param teacherBean
     */
    private void setTeacher(ViewHolder viewHolder, TeacherBean teacherBean) {
        RecyclerView recyclerView = viewHolder.recyclerView;
        recyclerView.removeItemDecoration(recyclerView.getItemDecorationAt(0));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        DoctorAdapter doctorAdapter = new DoctorAdapter(mContext);
        recyclerView.setAdapter(doctorAdapter);
        doctorAdapter.setData(teacherBean.item);
        int size = teacherBean.item.size();
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(UIUtil.dip2px(13),
                UIUtil.dip2px(4),
                UIUtil.dip2px(13),
                UIUtil.dip2px(4),size);
        recyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 设置banner
     * @param holder
     * @param bannerBean
     */
    private void setBanner(ViewHolder holder,BannerBean bannerBean) {
        Banner banner = holder.banner;
        String[] paths = bannerBean.paths;
        List<String> bannerIamgePaths= Arrays.asList(paths);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(bannerIamgePaths);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(bannerTitles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1800);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.NOT_INDICATOR);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = mList.get(position);
        if(o instanceof BannerBean){
            return BANNER;
        }else if(o instanceof TeacherBean){
            return TEACHER;
        }else if(o instanceof TitleBean){
            return TITLE;
        }else if(o instanceof NomalSub2Bean){
            return NOMAL;
        }else if(o instanceof LineBean){
            return LINE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager manager = (GridLayoutManager) layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if(type==NOMAL){
                        return 1;
                    }else{
                        return manager.getSpanCount();
                    }
                }
            });
        }
    }

    /**
     * 设置列表数据
     * @param data
     */
    public void setData(List<Object> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private Banner banner;
        private RecyclerView recyclerView;
        private TextView tvTeacher;
        private ImageView ivMore;
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvAddress;

        public ViewHolder(View itemView,int type) {
            super(itemView);
            switch (type){
                case BANNER:
                    banner=((Banner) itemView.findViewById(R.id.home_banner));
                    break;
                case TEACHER:
                    recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_teacher);
                    break;
                case TITLE:
                    tvTeacher = (TextView) itemView.findViewById(R.id.tv_teacher_item);
                    ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
                    break;
                case NOMAL:
                    ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
                    tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                    tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
                    break;
            }
        }
    }
}
