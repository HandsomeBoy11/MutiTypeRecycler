package com.xrd.mutityperecycler.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 2018/9/17.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private int itemCount;

    /**
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param itemCount 条目的数量
     */

    public SpaceItemDecoration(int left, int top, int right, int bottom,int itemCount) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.itemCount=itemCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if(position==0){
            outRect.left=left;
        }else {
            outRect.left=left/2;
        }
        if(itemCount>0&&position==itemCount-1){
            outRect.right=right;
        }
        outRect.top=top;
        outRect.bottom=bottom;
    }
}
