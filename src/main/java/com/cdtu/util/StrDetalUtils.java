package com.cdtu.util;

/**
 * 时间判断工具类（当前时间与一个时间范围的对比）
 */
public class StrDetalUtils {
    public static int dateComp(String date,String startDate,String endDate){
        String[] dates=date.split("-");
        String[] startTime=startDate.split("-");
        String[] endTime=endDate.split("-");
        Long now=0l;
        Long start=0l;
        Long end=0l;
        date="";
        for(int i=0;i<dates.length;i++){
            date+=dates[i];
        }
        now=Long.parseLong(date);
        startDate="";
        for(int i=0;i<startTime.length;i++){
            startDate+=startTime[i];
        }
        start=Long.parseLong(startDate);
        endDate="";
        for (int i=0;i<endTime.length;i++){
            endDate+=endTime[i];
        }
        end=Long.parseLong(endDate);
        if(now<start){
            return  -1;
        }else if(now>end){
            return 1;
        }else {
           return 0;
        }

    }
}
