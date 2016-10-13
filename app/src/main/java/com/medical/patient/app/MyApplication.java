package com.medical.patient.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.medical.patient.message.IM.MyMessageHandler;
import com.medical.patient.message.UniversalImageLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;

import cn.bmob.newim.BmobIM;

/**
 * Created by chenjun on 2016/10/7.
 */
public class MyApplication extends Application {
    private static Context mContext;

    public static Typeface typefaceLatoRegular = null;
    public static Typeface typefaceLatoHairline = null;
    public static Typeface typefaceLatoLight = null;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.mContext = getApplicationContext();

        /**
         * 初始化IM（只有主进程运行的时候才需要初始化）
         */
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(this); //im初始化
            BmobIM.registerDefaultMessageHandler(new MyMessageHandler(this)); //注册消息接收器
        }
        UniversalImageLoader.initImageLoader(this);//UI初始化

        initTypeface();
    }

    /**
     * 获取当前运行的进程名
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 初始化字体
    private static void initTypeface() {
        typefaceLatoRegular = Typeface.createFromAsset(
                mContext.getAssets(), "fonts/Lato-Regular.ttf");
        typefaceLatoHairline = Typeface.createFromAsset(
                mContext.getAssets(), "fonts/Lato-Hairline.ttf");
        typefaceLatoLight = Typeface.createFromAsset(
                mContext.getAssets(), "fonts/LatoLatin-Light.ttf");
    }

    // 获得字体
    public static Typeface getTypeface() {
        if (typefaceLatoLight == null)
            initTypeface();
        if ("en".equals(Locale.getDefault().getLanguage()))
            return typefaceLatoLight;
        if ("zh".equals(Locale.getDefault().getLanguage()))
            return Typeface.DEFAULT;
        return typefaceLatoLight;
    }

    public static Context getAppContext() {
        return MyApplication.mContext;
    }
}
