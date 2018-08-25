package com.hesvit.lecommon;

import android.support.mediacompat.BuildConfig;
import android.util.Log;

/**
 * @USER Administrator
 * @PACKAGE_NAME com.hesvit.leroycommon
 * @DATE 2018/8/25 13:52
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
 * @describe 统一Log日志管理
 * <p>
 * LOGLEVEL 表示以下几个值之一:
 * VERBOSE, DEBUG, INFO, WARN or ERROR
 * <p>
 * 默认日志的等级是DEBUG.
 * <p>
 * 在release版本日志默认是关闭的,如果要开启,更改{@link #ENABLE_LOGS_IN_RELEASE}的值.
 */

public final class Logger {

    /**
     * 日志默认TAG
     */
    public static final String LOG_TAG = "Leroy";

    /**
     * 日志调试TAG
     */
    public static final String DEBUG_TAG = "TT";

    /**
     * 数据请求流TAG
     */
    public static final String DATA_FLOW_TAG = "dataFlow";


    /**
     * 信鸽消息推送TAG
     */
    public static final String XG_TAG = "XINGE";

    /**
     * 是否允许在release版本显示日志
     */
    private static final boolean ENABLE_LOGS_IN_RELEASE = false;

    public static boolean canLog(int level) {
        return (ENABLE_LOGS_IN_RELEASE || BuildConfig.DEBUG);// && Log.isLoggable(LOG_TAG, level);
    }

    public static void d(String tag, String message) {
        if (canLog(Log.DEBUG)) {
            Log.d(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (canLog(Log.VERBOSE)) {
            Log.v(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (canLog(Log.INFO)) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message, Throwable thr) {
        if (canLog(Log.INFO)) {
            Log.i(tag, message, thr);
        }
    }

    public static void w(String tag, String message) {
        if (canLog(Log.WARN)) {
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String message, Throwable thr) {
        if (canLog(Log.WARN)) {
            Log.w(tag, message, thr);
        }
    }

    public static void e(String tag, String message) {
        if (canLog(Log.ERROR)) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable thr) {
        if (canLog(Log.ERROR)) {
            Log.e(tag, message, thr);
        }
    }
}
