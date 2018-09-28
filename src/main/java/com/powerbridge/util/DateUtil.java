package com.powerbridge.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
    public static final String DATETIME_FORMAT_1 = "yyyyMMddHHmmss";
    public static final String DATETIME_FORMAT_2 = "yyyyMMdd";
    public static final String DATETIME_FORMAT_3 = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_4 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_5 = "yyyy-MM-dd'T'HH:mm:ss+08:00";
    public static final String DATETIME_FORMAT_6 = "yyyyMMddHH";
    public static final String DATETIME_FORMAT_7 = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_FORMAT_8 = "yyyyMMddHHmmssSSS";
    public static final String DATETIME_FORMAT_9 = "yyyy-MM-dd ahh:mm:ss";
    public static final String DATETIME_FORMAT_10 = "MM月dd日ahh:mm";
    public static final String TIME_FORMAT_1 = "MMddHHmm";
    public static final String TIME_FORMAT_2 = "HHmmss";
    public static final String TIME_FORMAT_3 = "HH:mm:ss";


    /**
     * 返回日期時間 yyyyMMddHHmmss
     * @Title: get_yyyyMMddHHmmss
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyyMMddHHmmss() {
        return formatCurrentDateTime(DATETIME_FORMAT_1);
    }

    /**
     * 返回日期時間 yyyy-MM-dd HH:mm:ss
     * @Title: get_yyyy_MM_dd_HH_mm_ss
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyy_MM_dd_HH_mm_ss() {
        return formatCurrentDateTime(DATETIME_FORMAT_4);
    }

    /**
     * 返回日期時間 2016-08-09T16:16:14.345+08:00
     * @Title: get_UTCTime
     * @param @return
     * @return String
     * @throws */
    public static String get_UTCTime() {
        return formatCurrentDateTime(DATETIME_FORMAT_5);
    }

    /**
     * 返回日期 yyyyMMdd
     * @Title: get_yyyyMMdd
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyyMMdd() {
        return formatCurrentDateTime(DATETIME_FORMAT_2);
    }

    /**
     * 返回日期 yyyyMMddHH
     * @Title: get_yyyyMMddHH
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyyMMddHH() {
        return formatCurrentDateTime(DATETIME_FORMAT_6);
    }
    /**
     * 返回日期 yyyyMMddHH
     * @Title: get_yyyyMMddHH
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyyMMddHHMM() {
        return formatCurrentDateTime(DATETIME_FORMAT_7);
    }
    /**
     * 返回日期 yyyyMMddHHmmssSss
     * @Title: get_yyyyMMddHH
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyyMMddHHmmssSSS() {
        return formatCurrentDateTime(DATETIME_FORMAT_8);
    }

    public static String get_yyyyMMddaHHmmss() {
        return formatCurrentDateTime(DATETIME_FORMAT_9);
    }
    /**
     * 返回日期 yyyy-MM-dd
     * @Title: get_yyyy_MM_dd
     * @param @return
     * @return String
     * @throws */
    public static String get_yyyy_MM_dd() {
        return formatCurrentDateTime(DATETIME_FORMAT_3);
    }
    /**
     * 返回日期時間 MMddHHmm
     * @Title: get_MMddHHmm
     * @param @return
     * @return String
     * @throws */
    public static String get_MMddHHmm() {
        return formatCurrentDateTime(TIME_FORMAT_1);
    }
    /**
     * 返回時間 HHmmss
     * @Title: get_HHmmss
     * @param @return
     * @return String
     * @throws */
    public static String get_HHmmss() {
        return formatCurrentDateTime(TIME_FORMAT_2);
    }

    /**
     * 返回時間 HH:mm:ss
     * @Title: get_HH_mm_ss
     * @param @return
     * @return String
     * @throws */
    public static String get_HH_mm_ss() {
        return formatCurrentDateTime(TIME_FORMAT_3);
    }

    /**
     * 根據日曆格式化日期格式
     * @Title: formatCurrentDateTime
     * @param @param formatText
     * @param @return
     * @return String
     * @throws */
    public static String formatCurrentDateTime(String formatText) {
        Calendar calendar = Calendar.getInstance();
        return convertDateToStr(calendar.getTime(), formatText);
    }

    /**
     * 根據日期和格式返回日期(字符串)
     * @Title: convertDateToStr
     * @param @param date
     * @param @param pattern
     * @param @return
     * @return String
     * @throws*/
    public static String convertDateToStr(Date date, String pattern) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    /**
     * 將日期字符串轉換指定格式的字符串
     * @Title: formatDateByString
     * @param @param dataStr
     * @param @param patternRes
     * @param @param pattern
     * @param @return
     * @return String
     * @throws ParseException
     * @throws */
    public static String formatDateByString(String dataStr ,String patternRes ,String pattern) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(patternRes);
        Date date = sdf.parse(dataStr);
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 返回两个时间差 单位秒
     *
     * @param lastTime yyyyMMddHHmmss
     * @param thisTime yyyyMMddHHmmss
     * @return
     * @throws ParseException
     */
    public static Long getTimeR(String lastTime, String thisTime)
            throws ParseException {

        Long a = new SimpleDateFormat(DATETIME_FORMAT_1).parse(lastTime)
                .getTime() / 1000;

        Long b = new SimpleDateFormat(DATETIME_FORMAT_1).parse(thisTime)
                .getTime() / 1000;

        return a - b;
    }

    /**
     * 返回两个时间差 单位秒
     *
     * @param lastTime yyyy-MM-dd HH:mm:ss
     * @param thisTime yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Long getTimeRR(String lastTime, String thisTime)
            throws ParseException {

        Long a = new SimpleDateFormat(DATETIME_FORMAT_4).parse(lastTime)
                .getTime() / 1000;

        Long b = new SimpleDateFormat(DATETIME_FORMAT_4).parse(thisTime)
                .getTime() / 1000;

        return a - b;
    }


    /**
     * 返回時間戳
     * @param timeStr
     * @param pattern 日期格式
     * @return
     * @throws ParseException
     */
    public static Long getTimestamp(String timeStr,String pattern)
            throws ParseException {
        Long time = new SimpleDateFormat(pattern).parse(timeStr).getTime();
        return time;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1
     *            时间参数 1 格式：1990-01-01 12:00:00
     * @param str2
     *            时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * @param str 定义今天日期格式  如【yyyyMMddHHmmss】
     * @return
     */
    public static String getTodayFormat(String str) {
        // 定义返回日期格式
        if (str == null || "".equals(str.trim())){
            str = DATETIME_FORMAT_2;
        }

        Date d = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat(str);

        return formatter.format(d);
    }

    /**
     * 返回增加天數后的日期    yyyy-MM-dd
     * @param day  增加的天數
     * @return
     */
    public static String addDay(int day) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_3);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);
        return sdf.format(calendar.getTime());
    }

    /**
     * 返回增加天數后的日期
     * @param day  增加的天數
     * @return
     */
    public static String addDay(int day,String format) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);
        return sdf.format(calendar.getTime());
    }

    /**
     * 返回週幾,從週一算起;（默認從週日算起）
     * @Title: getWeek
     * @param @return
     * @return int
     * @throws */
    public static int getWeek(){
        Calendar cal=Calendar.getInstance();
        int[] i = {7,1,2,3,4,5,6};
        return i[cal.get(Calendar.DAY_OF_WEEK)-1];
    }

	/*public static void main(String[] args) {
		*//*String startDate = "2018-06-17 07:00:00";
		String endDate = "2018-06-18 09:00:00";
		String re = getDistanceTime(startDate, endDate);
		System.out.println(re);
		String[] ge = re.split("天");
		System.out.println(ge[0]);*//*

		String time = get_yyyy_MM_dd_HH_mm_ss();
		System.out.println(time);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = sdf.format(date);
		System.out.println(date2);


	}*/

}
