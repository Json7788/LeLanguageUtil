package com.hesvit.lecommon;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @USER Administrator
 * @PACKAGE_NAME com.hesvit.leroycommon
 * @DATE 2018/8/25 14:43
 * <p>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * @describe 利用google Gson对json字符串和对应的实体类进行相互转换
 */

public class JsonHelper {

    /**
     * 获取实体的json字符串
     *
     * @param value 实体
     * @return 实体的json字符串
     */
    public static String createJsonString(Object value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    /**
     * 根据json字符串,实体类型获取实例,用法:
     * <p>Login login = JsonHelper.getObject(jsonString,Login.class);</p>
     *
     * @param jsonString json字符串
     * @param cls        要转换的实体类型
     * @return 与json字符串对应的实例, 注意null值判断
     */
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            if (isValidJsonStr(jsonString)) {
                Gson gson = new Gson();
                t = gson.fromJson(jsonString, cls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 根据json字符串,实体类型获取实例集合,用法:
     * <p>List&lt; Login> list = JsonHelper.getList(jsonString, new TypeToken&lt;List&lt;Login>>(){}.getType() );</p>
     *
     * @param jsonString json字符串
     * @param type       要转换的实体类型集合Type
     * @return 与json字符串对应的实例集合
     */
    public static <T> List<T> getList(String jsonString, Type type) {
        List<T> list = new ArrayList<>();
        try {
            if (isValidJsonStr(jsonString)) {
                Gson gson = new Gson();
                list = gson.fromJson(jsonString, type);
            }
        } catch (Exception e) {
            Logger.e(Logger.DEBUG_TAG, "JsonHelper-->getList error:" + e.getMessage());
        }
        return list;
    }


    /**
     * 判断json字符串是否有效
     *
     * @param json 要判断的json字符串
     * @return true:有效,false:无效
     */
    public static boolean isValidJsonStr(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
        } catch (JsonParseException e) {
            return false;
        }
        return true;
    }



}
