package com.leroy.lelanguageutil;

/**
 * Created by allen on 2018/8/14.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class LeDataUtil {

    public static final String DATA_UTIL = "DataUtil";
    private static LeDataUtil helper;
    private SharedPreferences sharedPreferences;

    private LeDataUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(DATA_UTIL, Context.MODE_PRIVATE);
    }

    public static LeDataUtil getInstance(Context context) {
        if (helper == null) {
            helper = new LeDataUtil(context);
        }
        return helper;
    }


    public  void putInt(String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public  int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }


    public  void putString(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }


    public  void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }


    public  boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public  int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public  void remove(String key) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }
}
