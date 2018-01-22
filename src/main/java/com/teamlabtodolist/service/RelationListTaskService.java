package com.teamlabtodolist.service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamlabtodolist.entity.RelationListTask;
import com.teamlabtodolist.repository.RelationListTaskRepository;

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
		return (taskIdList.isEmpty()) ? null : relationListTaskRepository.findByTaskIdIn(taskIdList);
	}
	
	/**
	 * リストIdと紐づくタスク取得
	 * @param listId
	 * @return
	 */
	public List<RelationListTask> findByListId(Integer listId){
		return (listId == null || listId <= 0) ? null : relationListTaskRepository.findByListId(listId);
	}
	
	/**
	 * リストIDからタスクをカウント
	 * @param listId
	 * @return
	 */
	public Integer countByListId(Integer listId){
		return (listId == null || listId <= 0) ? null : relationListTaskRepository.countByListId(listId);
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
		return relationListTaskRepository.save(relationListTask);
	}
	
	/**
	 * 紐付けを削除する
	 * @param title
	 */
	public void deleteRelation(Integer listId, Integer taskId){
		if(listId == null || listId <= 0 || taskId == null || taskId <= 0)
			return;
		RelationListTask relationListTask = relationListTaskRepository.findByTaskId(taskId);
		relationListTaskRepository.delete(relationListTask.getId());
	}
}
