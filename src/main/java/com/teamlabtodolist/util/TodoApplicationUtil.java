package com.teamlabtodolist.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class TodoApplicationUtil {
    
    /**
     * 期日をyyyyMMddの形式でフロントに表示する
     * @param date
     * @return string
     */
    public static String convertDateToFrontType(Date date){
        if(date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }
    
    /**
     * stringで受け取った期限日をdateでデータベースに格納する
     * @param limitDate
     */
    public static Date convertDateToFrontType(String strDate){
        Date date = new Date();
        if(StringUtils.isEmpty(strDate))
            return date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return date = sdf.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
