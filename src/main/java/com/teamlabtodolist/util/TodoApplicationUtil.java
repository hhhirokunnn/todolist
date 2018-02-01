package com.teamlabtodolist.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class TodoApplicationUtil {
    
    /**
     * エスケープする文字のリスト
     */
    public static final Map<String, String> ESCAPE_SEQUENCE = new HashMap<String,String>(){{
        put("&", "&amp;");
        put("\"", "&quot;");
        put("<", "&lt;");
        put(">", "&gt;");
        put("'", "&#39;");
        }};
    
    /**
     * 期日をyyyyMMddの形式でフロントに表示する
     * @param date
     * @return string
     */
    public static String convertDateToFrontType(Date date){
        if(date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try{
            return sdf.format(date);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
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
        try{
            return date = sdf.parse(strDate);
        }catch(ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 対象にエスケープする文字列があった場合はエスケープする
     * ない場合はそのまま返す
     * @param title
     * @return
     */
    public static String translateEscapeSequence(String target){
        if(StringUtils.isEmpty(target))
            return target;
        String result = target;
        for(Map.Entry<String, String> escapeLiterature : ESCAPE_SEQUENCE.entrySet())
            result = target.replace(escapeLiterature.getKey(), escapeLiterature.getValue());
        System.out.println(result);
        return result;
    }
}
