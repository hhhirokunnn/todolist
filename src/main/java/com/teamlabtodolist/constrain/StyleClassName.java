package com.teamlabtodolist.constrain;

import org.springframework.util.StringUtils;

/**
 * フロントステータスごとにスタイルクラスを出しわけるためのenum
 * @author mukaihiroto.
 */
public enum StyleClassName {
    NOT_YET("1","notYet"),
    DONE("2","done"),
    LIMIT("3","limit")
    ;
    
    private String cd;
    
    private String className;
    
    private StyleClassName (String cd, String className){
        this.cd = cd;
        this.className = className;
    }
    
    /**
     * インスタンスを返す
     * @param cd
     * @return
     */
    public static StyleClassName of(String cd){
        if(StringUtils.isEmpty(cd))
            return null;
        for(StyleClassName obj : values()){
            if(obj.cd.equals(cd)){
                return obj;
            }
        }
        return null;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
