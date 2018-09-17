package com.xrd.mutityperecycler.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.xrd.mutityperecycler.app.MyApp;

/**
 * Created by puchunjie on 2018/1/11.
 * 尺寸转换的工具类
 */

public class UIUtil {
    //iPhone4 参数：3.5英寸，640*960
    //dp = px*0.485
    public static int dip2px(float dpValue) {
        final float scale = MyApp.instance.getResources().getDisplayMetrics().density; // 获取手机的屏幕的密度
        return (int) (dpValue * scale + 0.5f);
    }
    /** dip转成px  代码中用这个（代码中只能识别像素，而布局要用dp）*/

    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * scale + 0.5f);
        return px;
    }
    public static int dip2px(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * scale + 0.5f);
        return px;
    }

    public static double change(double a){
        return a * Math.PI  / 180;
    }

    public static double changeAngle(double a){
        return a * 180 / Math.PI;
    }


    /** px转成dip */

    public static int px2dip(Context context,int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
    /**
     * PX转SP
     * @param value
     * @return
     */
    public static int px2sp(Context context,int value) {
        float spScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / spScale + 0.5f);
    }

    /**
     * SP转PX
     * @param value
     * @return
     */
    public static int sp2px(Context context,float value) {
        float spScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * spScale + 0.5f);
    }
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context)
    {
        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }
    public static void showToast(Context context, int stringId){
        Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
    }
    public static void showToast(Context context,String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回资源文件下,字符数组
     * @param id  资源id
     * @return
     */
    public static String[] getStringArray(Context context,int id){
        return context.getResources().getStringArray(id);
    }
    public static String getString(Context context,int id){
        return context.getResources().getString(id);
    }

    public static Drawable getDrawable(Context context,int id){
        return context.getResources().getDrawable(id);

    }
    /**
     * 根据资源id  返回一个颜色
     * @param id
     * @return
     */
    public static int getColor(Context context, int id){
        return context.getResources().getColor(id);
    }
    /**
     * 创建View对象
     * @param layoutId  布局id
     * @return
     *
     */
    public static View inflate(Context context,int layoutId){
        return View.inflate(context, layoutId, null);
    }

    public static float getDimens(Context context,int id) {
        return context.getResources().getDimension(id);
    }

    public static String getUnit(String str,int limit){
        String retPrice = str;
        int price = Integer.valueOf(str);
        if(price >= limit){
            double temp = (double)price/limit;
            retPrice =  Math.floor(temp * 10)/10  + "w";
        }
        return retPrice;
    }

    public static float getSubScreenFloat(Context context,float subScreenHeight,int bottomLayoutHeiht){
        int avalibleHeight = getScreenHeight(context) - getStatusHeight(context);
        return 1.0f - subScreenHeight - (float)bottomLayoutHeiht/avalibleHeight;
    }
}
