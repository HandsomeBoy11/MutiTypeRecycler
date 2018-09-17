package com.xrd.mutityperecycler.bean;

import java.util.List;

/**
 * Created by user on 2018/9/17.
 */

public class NomalSub1Bean {
    public String title;
    public boolean isMore;
    public List<NomalSub2Bean> sub2BeanList;

    @Override
    public String toString() {
        return "NomalSub1Bean{" +
                "title='" + title + '\'' +
                ", isMore=" + isMore +
                ", sub2BeanList=" + sub2BeanList +
                '}';
    }
}
