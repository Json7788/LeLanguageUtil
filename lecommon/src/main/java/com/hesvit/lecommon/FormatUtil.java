package com.hesvit.lecommon;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @USER Administrator
 * @PACKAGE_NAME com.hesvit.leroycommon
 * @DATE 2018/8/25 14:54
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
 * @describe
 */

public class FormatUtil {

    /**
     * 文件大小单位格式,按大小转换为kb,mb,gb
     *
     * @param fileSize 文件大小,单位为字节
     */
    public static String formatFileSize(long fileSize) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fileSizeString;
        if (fileSize < 0) {
            fileSizeString = df.format(0) + "B";
        } else if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static String formatFileSizeToMB(long fileSize) {
        if (fileSize < 0) return "0";
        DecimalFormat df = new DecimalFormat("0");
        return df.format(fileSize / 1048576);
    }

    /**
     * 获取运动总时长格式字符串
     *
     * @param runtime 运动总时长,单位为分钟
     */
    public static String formatTotalRunTime(long runtime) {
        String value;
        if (runtime < 100) {//小于100分钟
            value = String.valueOf(runtime);
        } else {
            double hour = ((double) runtime) / 60;
            if (hour < 100) {//小于100小时
                DecimalFormat df = new DecimalFormat("0.00");
                value = df.format(hour);
            } else if (hour < 1000) {//小于1000小时
                DecimalFormat df = new DecimalFormat("0.0");
                value = df.format(hour);
            } else {
                value = String.valueOf((int) hour);
            }
        }
        return value;
    }

    /**
     * 获取中文格式的时间格式字符串
     *
     * @param iTime 总时长,单位为秒
     * @return 中文格式的时间格式字符串, 如:1时35分23秒
     */
    public static String formatTimeChinaStyle(long iTime) {
        long iHour = iTime / 3600;
        iTime = iTime % 3600;
        long iMinute = iTime / 60;
        long iSecond = iTime % 60;
        if (iHour != 0) {
            return String.format(Locale.getDefault(), "%d时%02d分%02d秒", iHour,
                    iMinute, iSecond);
        } else if (iMinute != 0) {
            return String.format(Locale.getDefault(), "%d分%02d秒",
                    iMinute, iSecond);
        } else {
            return String.format(Locale.getDefault(), "%d秒", iSecond);
        }
    }

//    /**
//     * 获取运动总时长单位格式字符串
//     *
//     * @param runtime 运动总时长,单位为分钟
//     */
//    public static String formatTotalRunTimeUnit(long runtime) {
//        int id;
//        if (runtime < 100) {//小于100分钟
//            id = R.string.minute;
//        } else {
//            id = R.string.hour;
//        }
//        return MixApp.getContext().getString(id);
//    }

    /**
     * 获取运动总卡路里格式字符串
     *
     * @param runCalorie 运动总卡路里,单位为大卡
     */
    public static String formatTotalCalorie(long runCalorie) {
        if (runCalorie < 100000) {
            return String.valueOf(runCalorie);
        } else if (runCalorie < 1000000) {
            DecimalFormat df = new DecimalFormat("0.00");
            return String.valueOf(df.format(runCalorie / 10000.0f));
        } else if (runCalorie < 10000000) {
            DecimalFormat df = new DecimalFormat("0.0");
            return String.valueOf(df.format(runCalorie / 10000.0f));
        } else {
            return String.valueOf(runCalorie / 10000);
        }
    }

//    /**
//     * 获取运动总卡路里单位格式字符串
//     *
//     * @param runCalorie 运动总卡路里,单位为大卡
//     */
//    public static String formatTotalCalorieUnit(long runCalorie) {
//        if (runCalorie < 100000) {
//            return MixApp.getContext().getString(
//                    R.string.calorie_unit);
//        } else {
//            return MixApp.getContext().getString(
//                    R.string.calorie_unit_w);
//        }
//    }

    /**
     * 获取俱乐部所有成员运动卡路里总和格式字符串
     *
     * @param calorie 俱乐部所有成员运动卡路里总和,单位为卡路里
     */
    public static String formatClubTotalCalorie(long calorie) {
        if (calorie < 100000) {
            return String.valueOf(calorie);
        } else if (calorie < 1000000) {
            DecimalFormat df = new DecimalFormat("0.00");
            return String.valueOf(df.format(calorie / 10000.0f));
        } else if (calorie < 10000000) {
            DecimalFormat df = new DecimalFormat("0.0");
            return String.valueOf(df.format(calorie / 10000.0f));
        } else {
            return String.valueOf(calorie / 10000);
        }
    }

    /**
     * 获取中文环境下,运动总步数格式字符串
     *
     * @param steps 运动总步数
     */
    public static String formatChineseTotalSteps(long steps) {
        String sResult;
        if (steps < 100000) {
            sResult = String.valueOf(steps);
        } else if (steps < 1000000) {
            DecimalFormat df = new DecimalFormat("0.00");
            sResult = df.format(steps / 10000.0f);
        } else if (steps < 10000000) {
            DecimalFormat df = new DecimalFormat("0.0");
            sResult = df.format(steps / 10000.0f);
        } else {
            sResult = String.valueOf(steps / 10000);
        }
        return sResult;
    }
//
//    /**
//     * 获取中文环境下,运动总步数单位格式字符串
//     *
//     * @param steps 运动总步数
//     */
//    public static String formatChineseTotalStepsUnit(long steps) {
//        if (steps < 100000) {
//            return MixApp.getContext().getString(R.string.steps_unit);
//        } else {
//            return MixApp.getContext().getString(R.string.steps_unit_w);
//        }
//    }

    /**
     * 获取英文环境下,运动总步数格式字符串
     *
     * @param steps 运动总步数
     */
    public static String formatTotalSteps(long steps) {
        DecimalFormat df = new DecimalFormat("0.00");
        String string;
        if (steps < 1024) {
            string = String.valueOf(steps);
        } else if (steps < 1048576) {
            string = df.format(steps / 1024);
        } else if (steps < 1073741824) {
            string = df.format(steps / 1048576);
        } else {
            string = df.format(steps / 1073741824);
        }
        return string;
    }

//    /**
//     * 获取英文环境下,运动总步数单位格式字符串
//     *
//     * @param steps 运动总步数
//     */
//    public static String formatTotalStepsUnit(long steps) {
//        String string;
//        if (steps < 1024) {
//            string = MixApp.getContext().getString(R.string.steps_unit);
//        } else if (steps < 1048576) {
//            string = String.format("%s%s", "k", MixApp.getContext().getString(R.string.steps_unit));
//        } else if (steps < 1073741824) {
//            string = String.format("%s%s", "m", MixApp.getContext().getString(R.string.steps_unit));
//        } else {
//            string = String.format("%s%s", "g", MixApp.getContext().getString(R.string.steps_unit));
//        }
//        return string;
//    }

    /**
     * 将距离米转化为距离公里字符串
     *
     * @param lDistance 距离,单位为米
     * @return 精确到小数点后两位的公里字符串
     */
    public static String formatDistance(long lDistance) {
        Double distance = lDistance*1.00 / 1000; //公里
        String sResult;
        if (distance < 1000) {
            DecimalFormat df = new DecimalFormat("0.00");
            sResult = df.format(lDistance / 1000.0f);
        } else if (distance < 10000) {
            DecimalFormat df = new DecimalFormat("0.0");
            sResult = df.format(lDistance / 1000.0f);
        } else {
            sResult = String.valueOf(distance);
        }
        return sResult;
    }

    /**
     * 根据距离和时间,获取每公里配速(表示每公里用多少时间)字符串
     *
     * @param distance 距离,单位为米
     * @param time     时间,单位为秒
     * @return 每公里配速字符串, 如 0'00''
     */
    public static String formatSpeed(long distance, long time) {
        if (distance == 0)
            return "0'00''";
        return formatTimeQuotesStyle(time * 1000 / distance);
    }

    /**
     * 根据速度,获取每公里配速(表示每公里用多少时间)字符串
     *
     * @param dSpeed 速度,单位为米/秒
     * @return 每公里配速字符串, 如 0'00''
     */
    public static String formatSpeedUseInRunMainActivity(double dSpeed) {
        int iTime;
        if (dSpeed > 0.4) {
            iTime = (int) (1000 / dSpeed);
            if (iTime < 105) {
                return "1'45''";
            } else if (iTime > 1195) {//19*60+55
                return "19'55''";
            }
        } else if (dSpeed > 0 && dSpeed < 0.4) {
            return "00'00''";
        } else if (dSpeed < 0) {
            iTime = (int) (1000 / dSpeed) * -1;
        } else {//==0
            return "00'00''";
        }

        return formatTimeQuotesStyle(iTime);
    }

    /**
     * 根据速度,获取每公里配速(表示每公里用多少时间)字符串
     *
     * @param dSpeed 速度,单位为米/秒
     * @return 每公里配速字符串, 如 0'00''
     */
    public static String formatSpeed(double dSpeed) {
        int iTime;
        if (dSpeed > 0) {
            iTime = (int) (1000 / dSpeed);
        } else if (dSpeed < 0) {
            iTime = (int) (1000 / dSpeed) * -1;
        } else {//==0
            return "99'99''";
        }
        return formatTimeQuotesStyle(iTime);
    }


    /**
     * 根据1公里用的秒数,获取每公里配速(表示每公里用多少时间)字符串
     *
     * @param time 1公里用的秒数
     * @return 每公里配速字符串, 如 0'00''
     */
    public static String formatTimeQuotesStyle(long time) {
        long iMinute;
        long iSecond;

        String sResult = "";
        if (time < 0) {
            sResult = "-";
            time = -time;
        }
        iMinute = time / 60;
        iSecond = time % 60;

        if (iMinute > 99) {
            iMinute = 99;
            iSecond = 59;
        }

        if (iMinute >= 10) sResult += (iMinute / 10);

        sResult += iMinute % 10 + "'" + iSecond / 10 + ""
                + iSecond % 10 + "''";
        return sResult;

    }

    /**
     * 将运动时长转化为格式hh:mm:ss字符串
     *
     * @param time 运动时长,单位为秒
     */
    public static String formatRunTime(long time) {
        long iHour = time / 3600;
        time = time % 3600;
        long iMinute = time / 60;
        long iSecond = time % 60;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", iHour,
                iMinute, iSecond);
    }

    /**
     * 获取手表分组数据的每圈时间
     *
     * @param time 1圈用的秒数
     * @return 每公里配速字符串, 如 0'00
     */
    public static String formatPacketDataTime(long time) {
        time = time % 3600;
        long iMinute = time / 60;
        long iSecond = time % 60;

        return String.format(Locale.getDefault(), "%d'%02d", iMinute,
                iSecond);
    }

    /**
     * 获取运动目标配速格式字符串
     *
     * @param time 1公里用的秒数
     * @return 每公里配速字符串, 如 0'00''
     */
    public static String formatRunTargetPace(long time) {
        time = time % 3600;
        long iMinute = time / 60;
        long iSecond = time % 60;

        return String.format(Locale.getDefault(), "%d'%02d''", iMinute,
                iSecond);
    }

    /**
     * 获取音乐时长格式字符串
     *
     * @param iTime 时长,单位为秒
     * @return 音乐时长格式字符串, 如01:12:12
     */
    public static String formatMusicTime(long iTime) {
        long iMinute = iTime / 60;
        long iSecond = iTime % 60;
        long iHour = iSecond / 60;
        if (iHour > 0) {
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", iHour, iMinute, iSecond);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", iMinute, iSecond);
        }
    }


    /**
     * 获取某月的最后一天
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());//lastDayOfMonth
    }


    /*
     * 将时间转换为时间戳
     */
    public static Long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);

        return date.getTime();//timeStamp
    }


    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = Long.valueOf(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取前一月的最后一天的时间戳
     */
    public static String getLastDayOfLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();//获取当前日期
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为最后1天,当前日期既为本月最后一天
        return format.format(calendar.getTime());//firstDay
    }

    /**
     * 获取当前月的最后一天的时间戳
     */

    public static String getLastDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime());//lastDayOfMonth
    }

    /**
     * 获取精确到小数点后两位数格式
     */
    public static String getTwoPointsNumber(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }

    public static String getIntNumber(double num) {
        DecimalFormat df = new DecimalFormat("0");
        return df.format(num);
    }

}
