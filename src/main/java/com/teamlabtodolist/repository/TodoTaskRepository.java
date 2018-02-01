package com.teamlabtodolist.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.teamlabtodolist.entity.TodoTask;

/**
 * タスクのrepository
 * @author mukaihiroto
 * 
 */
@Transactional
public interface TodoTaskRepository extends JpaRepository <TodoTask, Integer> {
    
    Integer countByTitleContaining(String taskTitle);
    
    List<TodoTask> findByIdInOrderByCreatedDesc(HashSet<Integer> taskIds);
    
    List<TodoTask> findByTitleContainingOrderByCreatedDesc(String taskTitle);
    
    @Query(nativeQuery = true, value = "select t.* from todo_task t left join relation_list_task r on t.id = r.task_id where r.list_id = ?1 and t.status_cd = 1 order by t.limit_date asc limit 1")
    TodoTask findTaskByListId(Integer listId);
    
    @Query(nativeQuery = true, value = "select l.id as list_id count(r.id) as num_all_tasks from todo_list l left join relation_list_task r on l.id = r.list_id where l.id in ?1")
    List<Map<String,Object>> countAllTasksByListId(HashSet<Integer> listIds);
    
    @Query(nativeQuery = true, value = "select count(t.id) from todo_list l left join relation_list_task r on l.id = r.list_id left join todo_task t on r.task_id = t.id where l.id = ?1 and t.status_cd = 2 group by l.id")
    Integer countCompleteTasksByListId(Integer listId);
}