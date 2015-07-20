package tm.davidwang.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeDate {

    public static long getNowTimeline(){
        return Calendar.getInstance().getTimeInMillis();
    }

	
	public static String getShortTime(long dateline) {
        String shortstring = null;
        String time = timestampToStr(dateline);
        Date date = getDateByString(time);
        if(date == null) return shortstring;
        
        long now = Calendar.getInstance().getTimeInMillis();
        long deltime = (now - date.getTime())/1000;
        if(deltime > 365*24*60*60) {
            shortstring = (int)(deltime/(365*24*60*60)) + "年前";
        } else if(deltime > 24*60*60) {
            shortstring = (int)(deltime/(24*60*60)) + "天前";
        } else if(deltime > 60*60) {
            shortstring = (int)(deltime/(60*60)) + "小时前";
        } else if(deltime > 60) {
            shortstring = (int)(deltime/(60)) + "分前";
        } else if(deltime > 1) {
            shortstring = deltime + "秒前";
        } else {
            shortstring = "刚刚";
        }
        return shortstring;
    } 
	
	//Timestamp转化为String:
    public static String timestampToStr(long dateline){
        Timestamp timestamp = new Timestamp(dateline);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
       return df.format(timestamp);
    }
    
    public static Date getDateByString(String time) {
        Date date = null;
        if (time == null)
            return date;
        String date_format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(date_format);
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
