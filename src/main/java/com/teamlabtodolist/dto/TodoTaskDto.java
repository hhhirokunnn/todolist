package com.teamlabtodolist.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.teamlabtodolist.constrain.TaskStatus;
import com.teamlabtodolist.entity.TodoTask;
import com.teamlabtodolist.util.TodoApplicationUtil;

/**
 * タスクのDTO
 * @author mukaihiroto
 * 
 */
public class TodoTaskDto {
    
    private Integer listId;
    private Integer taskId;
    private String listTitle;
    private String taskTitle;
    private String statusCd;
    private String status;
    private Date taskCreated;
    private String frontTaskLimitDate;
    private Date taskLimitDate;
    private String frontTaskStyle;
    
    public TodoTaskDto(){}

    public TodoTaskDto (TodoTask todoTask){
        this.taskId = todoTask.getId();
        this.taskTitle = todoTask.getTitle();
        this.statusCd = todoTask.getStatusCd();
        this.status = getStatusByStatusCd(todoTask.getStatusCd());
        this.taskCreated = todoTask.getCreated();
        this.taskLimitDate = todoTask.getLimitDate();
        this.frontTaskLimitDate = TodoApplicationUtil.convertDateToFrontType(todoTask.getLimitDate());
        this.frontTaskStyle = getStyleClassName(todoTask.getStatusCd(), todoTask.getLimitDate());
    }
    
    public Integer getListId(){
        return this.listId;
    }
    public void setListId(Integer listId){
        this.listId = listId;
    }
    
    public Integer getTaskId(){
        return this.taskId;
    }
    public void setTaskId(Integer taskId){
        this.taskId = taskId;
    }
    
    public String getListTitle(){
        return this.listTitle;
    }
    public void setListTitle(String listTitle){
        this.listTitle = listTitle;
    }
    
    public String getTaskTitle(){
        return this.taskTitle;
    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    
    public String getStatusCd(){
        return this.statusCd;
    }
    public void setStatusCd(String statusCd){
        this.statusCd = statusCd;
    }
    
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    
    /**
     * タスクのcreatedをyyyy年MM月dd日の型で取得
     * @return
     */
    public String getTaskCreated(){
        return TodoApplicationUtil.convertDateToFrontType(this.taskCreated);
    }
    
    public void setTaskCreated(Date taskCreated){
        this.taskCreated = taskCreated;
    }
    
    public Date getTaskLimitDate(){
        return this.taskLimitDate;
    }
    
    public void setTaskLimitDate(Date taskLimitDate){
        this.taskLimitDate = taskLimitDate;
    }
    
    public String getFrontTaskLimitDate(){
        return this.frontTaskLimitDate;
    }
    
    public void setFrontTaskLimitDate(String frontLimitDate){
        this.frontTaskLimitDate = frontTaskLimitDate;
    }
    
    /**
     * stringで受け取った期限日をdateでデータベースに格納する
     * @param limitDate
     */
    public void setLimitDateFromFront(String date){
        this.taskLimitDate = TodoApplicationUtil.convertDateToFrontType(date);
    }
    
    /**
     * ステータスコードからステータスを取得
     * @param statusCd
     * @return
     */
    public String getStatusByStatusCd(String statusCd){
        if(StringUtils.isEmpty(statusCd))
            return "";
        TaskStatus taskStatus = TaskStatus.of(statusCd);
        return taskStatus == null ? "" : taskStatus.getStatusVal();
    }
    
    /**
     * ステータスコードからステータスをセット
     * @param statusCds
     * @return
     */
    public void setStatusByStatusCd(String statusCd){
        this.status = getStatusByStatusCd(statusCd);
    }
    
    public String getFrontTaskStyle(){
        return this.frontTaskStyle;
    }
    
    public void setFrontTaskStyle(String styleByStatus){
        this.frontTaskStyle = frontTaskStyle;
    }

    /**
     * フロントステータスごとにスタイルクラスを出しわける
     * @param statusCd
     * @param taskLimitDate
     * @return
     */
    public String getStyleClassName(String statusCd, Date taskLimitDate){
        if(StringUtils.isEmpty(statusCd) || taskLimitDate == null)
            return "";
        Date now = new Date();
        String styleStatus = now.after(taskLimitDate) ? "3" : statusCd;
        switch (styleStatus){
        case "1":
            return "notYet";
        case "2":
            return "done";
        case "3":
            return "limit";
        default:
            return "";
        }
    }
}
