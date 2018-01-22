package com.teamlabtodolist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
 
/**
 * リストのEntity
 * @author mukaihiroto
 * 
 */
@Entity
@Table(name="todo_list")
public class TodoList {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NumberFormat
    private Integer id;
 
    /** TODOLIST のtitle */
    @Column(name="title")
    private String title;
    
    /** TODOLIST の作成日 */
    @Column(name="created")
    @DateTimeFormat
    private Date created;
    
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Date getCreated() {
        return created;
    }
 
    public void setCreated(Date created) {
        this.created = created;
    }
    
}