package com.xrd.mutityperecycler.bean;

/**
 * Created by user on 2018/9/14.
 */

public class TitleBean {
    public String titleName;
    public boolean isMore;

    public TitleBean(String titleName, boolean isMore) {
        this.titleName = titleName;
        this.isMore = isMore;
    }

    @Override
    public String toString() {
        return "TitleBean{" +
                "titleName='" + titleName + '\'' +
                ", isMore=" + isMore +
                '}';
    }
}
