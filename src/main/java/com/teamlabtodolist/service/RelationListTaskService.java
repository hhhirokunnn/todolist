package com.teamlabtodolist.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamlabtodolist.entity.RelationListTask;
import com.teamlabtodolist.repository.RelationListTaskRepository;

/**
 * 紐付けのサービス
 * @author mukaihiroto
 * 
 */
@Service
@Transactional
public class RelationListTaskService {
    
    @Autowired
    private RelationListTaskRepository relationListTaskRepository;
    
    /**
     * タスクIdと紐づくリスト
     * @param listId
     * @return
     */
    public List<RelationListTask> findAllByTaskId(HashSet<Integer> taskIdList){
        try{
            return (taskIdList.isEmpty()) ? Collections.emptyList()  : relationListTaskRepository.findByTaskIdIn(taskIdList);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * リストIdと紐づくタスク取得
     * @param listId
     * @return
     */
    public List<RelationListTask> findByListId(Integer listId){
        try{
            return (listId == null || listId <= 0) ? Collections.emptyList() : relationListTaskRepository.findByListId(listId);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * リストIDからタスクをカウント
     * @param listId
     * @return
     */
    public Integer countByListId(Integer listId){
        try{
            return (listId == null || listId <= 0) ? 0 : relationListTaskRepository.countByListId(listId);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * 紐付けを作成する
     * @param title
     */
    public RelationListTask createRelation(Integer listId, Integer taskId){
        if(listId == null || listId <= 0 || taskId ==null || taskId <= 0)
            return null;
        RelationListTask relationListTask = new RelationListTask();
        relationListTask.setListId(listId);
        relationListTask.setTaskId(taskId);
        try{
            return relationListTaskRepository.save(relationListTask);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * 紐付けを削除する
     * @param title
     */
    public void deleteRelation(Integer listId, Integer taskId){
        if(listId == null || listId <= 0 || taskId == null || taskId <= 0)
            return;
        try{
            RelationListTask relationListTask = relationListTaskRepository.findByTaskId(taskId);
            relationListTaskRepository.delete(relationListTask.getId());
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
