package com.teamlabtodolist.constraints;

import java.util.Optional;

import org.springframework.util.StringUtils;

/**
 * タスクのステータス用のENUM
 * @author mukaihiroto.
 */
public enum TaskStatus {
    NOT_YET("1","未完了"),
    DONE("2","完了")
    ;
    
    private String statusCd;
    
    private String statusVal;
    
    private TaskStatus(String statusCd,String statusVal){
        this.statusCd = statusCd;
        this.statusVal = statusVal;
    }
    
    /**
     * タスクのステータスのインスタンスを返す
     * @param cd
     * @return
     */
    public static Optional<TaskStatus> of(String cd){
        if(StringUtils.isEmpty(cd))
            return Optional.empty();
        for(TaskStatus obj : values()){
            if(obj.statusCd.equals(cd))
                return Optional.of(obj);
        }
        return Optional.empty();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getStatusVal() {
        return statusVal;
    }

    public void setStatusVal(String statusVal) {
        this.statusVal = statusVal;
    }
    
    
}
