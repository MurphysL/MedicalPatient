package com.medical.patient.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chenjun on 2016/9/25.
 */
public class SharedPrefsUtil {
    public final static String Tag = "Patient";

    public static void putStringValue(Context context, String key, String value) {
        // 1. 实例化sharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences(Tag, Activity.MODE_PRIVATE);
        // 2. 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        // 3. 用putString保存数据
        editor.putString(key, value);
        // 4. 提交当前数据
        editor.commit();
    }

    public static void putIntValue(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(Tag, Activity.MODE_PRIVATE);
        // 2. 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        // 3. 用putString保存数据
        editor.putInt(key, value);
        // 4. 提交当前数据
        editor.commit();
    }

    public static String getStringValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Tag, Activity.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
    }

    public static int getIntValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Tag, Activity.MODE_PRIVATE);
        int value = preferences.getInt(key, -1);
        return value;
    }
}
