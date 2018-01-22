package com.teamlabtodolist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * TODOTASKのEntity
 * @author mukaihiroto
 *
 */
@Entity
@Table(name="todo_task")
public class TodoTask {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
 
    /** TODOTASK のtitle */
    @Column(name="title")
    private String title;
    
    /** TODOTASK のstatus */
    @Column(name="status_cd")
    private String statusCd;
    
    /** TODOTASK の作成日 */
    @Column(name="created")
    private Date created;
    
    /** TODOTASK の期限日 */
    @Column(name="limit_date")
    private Date limitDate;
 
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
    
    public String getStatusCd() {
        return statusCd;
    }
 
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }
    
    public Date getCreated() {
        return created;
    }
 
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getLimitDate() {
        return limitDate;
    }
 
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }
    
}