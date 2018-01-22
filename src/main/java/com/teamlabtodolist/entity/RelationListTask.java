package com.teamlabtodolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * リストとタスクの紐付けのEntity
 * @author mukaihiroto
 * 
 */
@Entity
@Table(name="relation_list_task")
public class RelationListTask {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
 
    /** TODOLIST のid */
    @Column(name="list_id")
    private Integer listId;
    
    /** TODOTASK のid日 */
    @Column(name="task_id")
    private Integer taskId;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public Integer getListId() {
        return listId;
    }
 
    public void setListId(Integer listId) {
        this.listId = listId;
    }
    
    public Integer getTaskId() {
        return taskId;
    }
 
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
}