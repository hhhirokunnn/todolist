package com.teamlabtodolist.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.teamlabtodolist.entity.TodoList;

/**
 * リストのDto
 * @author mukaihiroto
 * 
 */
public class TodoListDto {
	
	private Integer listId;
	private String listTitle;
	private Date listCreated;
	private Integer countAllTask;
	private Integer countCompleteTask;
	private Date taskLimitDate;
	private String frontTaskLimitDate;

	public TodoListDto (){}
	
	public TodoListDto (TodoList todoList){
		this.listId = todoList.getId();
		this.listTitle = todoList.getTitle();
		this.listCreated = todoList.getCreated();
	}
	
	public Integer getListId(){
		return this.listId;
	}
	public void setListId(Integer listId){
		this.listId = listId;
	}
	
	public String getListTitle(){
		return this.listTitle;
	}
	
	public void setListTitle(String listTitle){
		this.listTitle = listTitle;
	}
	
	/**
	 * createdをyyyy年MM月dd日の型で取得
	 * @return
	 */
	public String getListCreated(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try{
        	return sdf.format(this.listCreated);
        }catch(Exception e){
        	e.printStackTrace();
        }
		return "";
	}
	
	public void setListCreated(Date listCreated){
		this.listCreated = listCreated;
	}
	
	
	public Integer getCountAllTask(){
		return this.countAllTask;
	}
	
	public void setCountAllTask(Integer countAllTask){
		this.countAllTask = countAllTask;
	}
	
	public Integer getCountCompleteTask(){
		return this.countCompleteTask;
	}
	
	public void setCountCompleteTask(Integer countCompleteTask){
		this.countCompleteTask = countCompleteTask;
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
	
	/**
	 * 期日をyyyyMMddの形式でフロントに表示する
	 * @param limitDate
	 * @return
	 */
	public String getFrontTaskLimitDateByLimitDate(Date limitDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try{
        	return sdf.format(limitDate);
        }catch(Exception e){
        	e.printStackTrace();
        }
		return "";
	}
	
	public void setFrontTaskLimitDate(String frontTaskLimitDate){
		this.frontTaskLimitDate = frontTaskLimitDate;
	}
}
