package com.teamlabtodolist.constraints;

import java.util.Optional;

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
    
    private final String cd;
    
    private final String resultMessage;
    
    private CreationResult (String cd, String resultMessage){
        this.cd = cd;
        this.resultMessage = resultMessage;
    }
    
    public String getCd() {
        return cd;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * インスタンスを返す
     * @param cd
     * @return
     */
    public static Optional <CreationResult> of(String cd){
        if(StringUtils.isEmpty(cd))
            return Optional.empty();
        for(CreationResult obj : values()){
            if(obj.cd.equals(cd)){
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }
}
