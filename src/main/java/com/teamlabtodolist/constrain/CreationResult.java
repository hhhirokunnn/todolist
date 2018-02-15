package com.teamlabtodolist.constrain;

import org.springframework.util.StringUtils;

/**
 * バリデーションの結果文言
 * @author mukaihiroto.
 */
public enum CreationResult {
    CREATION_SUCCESS("1","作成しました"),
    TITLE_EMPTY("2","タイトルが未入力"),
    TITLE_OUT_OF_RANGE("3","タイトルが文字制限の範囲外"),
    TITLE_DUOLICATION("4","タイトルが重複"),
    LIMIT_DATE_EMPTY("5","期限が未入力"),
    DTO_NULL("6","DTOがnull"),
    ;
    
    private String cd;
    
    private String resultMessage;
    
    private CreationResult (String cd, String resultMessage){
        this.cd = cd;
        this.resultMessage = resultMessage;
    }
    
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * インスタンスを返す
     * @param cd
     * @return
     */
    public static CreationResult of(String cd){
        if(StringUtils.isEmpty(cd))
            return null;
        for(CreationResult obj : values()){
            if(obj.cd.equals(cd)){
                return obj;
            }
        }
        return null;
    }

}
