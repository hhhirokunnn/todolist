package com.teamlabtodolist.repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.teamlabtodolist.entity.RelationListTask;

/**
 * 紐付けのrepository
 * @author mukaihiroto
 * 
 */
public interface RelationListTaskRepository extends JpaRepository <RelationListTask, Integer> {
    
    RelationListTask findByTaskId(Integer taskId);
    
    List<RelationListTask> findByListId(Integer listId);
    
    List<RelationListTask> findByTaskIdIn(HashSet<Integer> taskIds);
    
    Integer countByListId(Integer listId);
    
}