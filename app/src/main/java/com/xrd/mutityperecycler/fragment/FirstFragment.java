package com.xrd.mutityperecycler.fragment;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xrd.mutityperecycler.R;
import com.xrd.mutityperecycler.adapter.RvAdapter;
import com.xrd.mutityperecycler.bean.BannerBean;
import com.xrd.mutityperecycler.bean.LineBean;
import com.xrd.mutityperecycler.bean.NomalBean;
import com.xrd.mutityperecycler.bean.NomalSub1Bean;
import com.xrd.mutityperecycler.bean.NomalSub2Bean;
import com.xrd.mutityperecycler.bean.TeacherBean;
import com.xrd.mutityperecycler.bean.TeacherItemBean;
import com.xrd.mutityperecycler.bean.TitleBean;
import com.xrd.mutityperecycler.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by user on 2018/9/14.
 */

public class FirstFragment extends BaseFragment {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private RvAdapter rvAdapter;
    private Object bannerData;
    private List<Object> dataList;
    private ItemDecoration itemDecoration;
    private Object nomalData;

    @Override
    public void initDataAndEnvnt() {
        dataList = new ArrayList<>();
//        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        getBannerData();//banner数据
        getTeacherData();//推荐名师数据初始化
        getNomalData();//获得普通列表数据
    }

    /**
     * 获得普通列表数据
     */
    public void getNomalData() {
        refresh.finishRefresh(3000);
        NomalBean nomalBean = new NomalBean();
        List<NomalSub1Bean> list1=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NomalSub1Bean nomalSub1Bean = new NomalSub1Bean();
            nomalSub1Bean.title="精品课程"+i;
            if(i%2==0){
                nomalSub1Bean.isMore=true;
            }
            List<NomalSub2Bean> list2=new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                NomalSub2Bean nomalSub2Bean = new NomalSub2Bean();
                nomalSub2Bean.address="国医学堂";
                nomalSub2Bean.content="北京市中医药服务贸易与文化交流大会"+j;
                if(j%2==0){
                    nomalSub2Bean.isFirst=true;
                }else{
                    nomalSub2Bean.isFirst=false;
                }
                list2.add(nomalSub2Bean);
            }
            list1.add(nomalSub1Bean);
            nomalSub1Bean.sub2BeanList=list2;
        }
        nomalBean.sub1BeanList=list1;

        processNomaldata(nomalBean);
    }

    private void processNomaldata(NomalBean nomalBean) {
        if(nomalBean!=null){
            List<NomalSub1Bean> sub1BeanList = nomalBean.sub1BeanList;
            for (int i = 0; i < sub1BeanList.size(); i++) {
                NomalSub1Bean nomalSub1Bean = sub1BeanList.get(i);
                LineBean lineBean = new LineBean();
                dataList.add(lineBean);
                TitleBean titleBean = new TitleBean(nomalSub1Bean.title, nomalSub1Bean.isMore);
                dataList.add(titleBean);
                List<NomalSub2Bean> sub2BeanList = nomalSub1Bean.sub2BeanList;
                for (int j = 0; j <sub2BeanList.size() ; j++) {
                    NomalSub2Bean nomalSub2Bean = sub2BeanList.get(j);
                    dataList.add(nomalSub2Bean);
                }
            }
            // 设置数据到列表
            rvAdapter.setData(dataList);
            if(dataList.size()>0){
                tvNoData.setVisibility(View.GONE);
            }else{
                tvNoData.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 获得推荐名师数据
     */
    private void getTeacherData() {
        TeacherBean teacherBean = new TeacherBean();
        List<TeacherItemBean> items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TeacherItemBean teacherItemBean = new TeacherItemBean();
            teacherItemBean.imageUrl = "abc";
            teacherItemBean.name = "名师" + i;
            items.add(teacherItemBean);
            teacherBean.item = items;
        }
        TitleBean titleBean = new TitleBean("推荐名师", false);
        dataList.add(titleBean);
        dataList.add(teacherBean);

    }

    /**
     * 获得banner数据
     */
    public void getBannerData() {
        String[] bannerPaht = getResources().getStringArray(R.array.banner_paht);
        BannerBean bannerBean = new BannerBean();
        bannerBean.paths = bannerPaht;
        dataList.add(bannerBean);

    }

    @Override
    public void initView() {
        ItemDecoration itemDecoration = getItemDecoration();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rvContent.setLayoutManager(gridLayoutManager);
        rvAdapter = new RvAdapter(mContext);
        rvContent.setAdapter(rvAdapter);
        rvContent.addItemDecoration(itemDecoration);
        refresh.setRefreshHeader(new ClassicsHeader(getContext()));
        refresh.setRefreshFooter(new ClassicsFooter(getContext()));
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.setNoMoreData(true);
                refreshLayout.finishLoadMore(3000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getNomalData();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first;
    }

    @OnClick(R.id.tv_no_data)
    public void onViewClicked() {
        getData();
    }

    /**
     * 设置条目的偏移量
     * @return
     */
    public ItemDecoration getItemDecoration() {
        itemDecoration = new ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (dataList == null || dataList.size() == 0) {
                    return;
                }
                int position = parent.getChildAdapterPosition(view);
                Object item = dataList.get(position);
                if (item instanceof TitleBean) {
                    outRect.left = UIUtil.dip2px(13);
                    outRect.right = UIUtil.dip2px(13);
                } else if (item instanceof NomalSub2Bean) {
                    NomalSub2Bean nomalSub2Bean = (NomalSub2Bean) item;
                    if(nomalSub2Bean.isFirst){
                        outRect.left = UIUtil.dip2px(13);
                        outRect.right = UIUtil.dip2px(3);
                    }else{
                        outRect.left=UIUtil.dip2px(3);
                        outRect.right = UIUtil.dip2px(13);
                    }
                    outRect.top = UIUtil.dip2px(6);
                    outRect.bottom = UIUtil.dip2px(6);
                }

            }
        };
        return itemDecoration;
    }

}
