package com.leroy.lelanguageutil;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import static android.content.ContentValues.TAG;

public class LeMultiLanguageUtil {
    public static final String LANGUAGE_SETTING = "LANGUAGE_SETTING";
    private static LeMultiLanguageUtil instance;
    private Context context;
    private static LeLanguageChangedListener languageChangedListener;

    public static void init(Context context) {

        if (instance == null) {
            synchronized (LeMultiLanguageUtil.class) {
                if (instance == null) {
                    instance = new LeMultiLanguageUtil(context);
                }
            }
        }
    }

    public static void setLanguageChangedListener(LeLanguageChangedListener languageChangedListener) {
        LeMultiLanguageUtil.languageChangedListener = languageChangedListener;
    }

    public static LeMultiLanguageUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("You must be init LeMultiLanguageUtil first");
        }
        return instance;
    }

    private LeMultiLanguageUtil(Context context) {
        this.context = context;
    }


    /**
     * 设置语言
     */
    public void setConfiguration() {
        Locale targetLocale = getLanguageLocale();
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    private Locale getLanguageLocale() {
        int languageType = LeDataUtil.getInstance(context).getInt(LANGUAGE_SETTING, LeMultiLanguageType.LANGUAGE_CHINESE);
//        if (languageType == LeMultiLanguageType.LANGUAGE_SYSTEM) {
//            Locale sysLocale = getSysLocale();
//            return sysLocale;
//        } else
        if (languageType == LeMultiLanguageType.LANGUAGE_ENGLISH) {
            return Locale.ENGLISH;
        } else if (languageType == LeMultiLanguageType.LANGUAGE_CHINESE) {
            return Locale.SIMPLIFIED_CHINESE;
        }
//        else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
//            return Locale.TRADITIONAL_CHINESE;
//        }
        getSystemLanguage(getSysLocale());
        Log.e(TAG, "getLanguageLocale" + languageType + languageType);
        return Locale.SIMPLIFIED_CHINESE;
    }

    private String getSystemLanguage(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();

    }

    //以上获取方式需要特殊处理一下
    public Locale getSysLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    /**
     * 更新语言
     *
     * @param languageType
     */
    public void updateLanguage(int languageType) {
        LeDataUtil.getInstance(context).putInt(LANGUAGE_SETTING, languageType);
        getInstance().setConfiguration();
        if (languageChangedListener != null) {
            languageChangedListener.onLanguageChanged();
        }
//        EventBus.getDefault().post(new OnChangeLanguageEvent(languageType));
    }

//    public String getLanguageName(Context context) {
//        int languageType = LeDataUtil.getInstance(context).getInt(LANGUAGE_SETTING, LeMultiLanguageType.LANGUAGE_SYSTEM);
//        if (languageType == LeMultiLanguageType.LANGUAGE_ENGLISH) {
//            return context.getString(R.string.setting_language_english);
//        } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
//            return context.getString(R.string.setting_simplified_chinese);
//        }
////        else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
////            return context.getString(R.string.setting_traditional_chinese);
////        }
//        return context.getString(R.string.setting_language_auto);
//    }

    /**
     * 获取到用户保存的语言类型
     *
     * @return
     */
    public int getLanguageType() {
        int languageType = LeDataUtil.getInstance(context).getInt(LANGUAGE_SETTING, LeMultiLanguageType.LANGUAGE_CHINESE);

        return languageType;
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context);
        } else {
            getInstance().setConfiguration();
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = getInstance().getLanguageLocale();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
