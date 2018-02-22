package com.teamlabtodolist.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.teamlabtodolist.constraints.CreationResult;
import com.teamlabtodolist.dto.TodoListDto;
import com.teamlabtodolist.entity.TodoList;
import com.teamlabtodolist.entity.TodoTask;
import com.teamlabtodolist.repository.TodoListRepository;
import com.teamlabtodolist.util.TodoApplicationUtil;

/**
 * タスクのサービス
 * @author mukaihiroto
 *
 */
@Service
@Transactional
public class TodoListService {
    
    @Autowired
    private TodoListRepository todoListRepository;
    
    @Autowired
    private TodoTaskService todoTaskService;
    
    @Autowired
    private RelationListTaskService relationListTaskService;
    
    /**
     * 全件検索
     * @return
     */
    public List<TodoList> findAll(){
        return todoListRepository.findAll();
    }
    
    /**
     * Idによる検索
     * @param id
     * @return
     */
    public TodoList searchById(Integer listId){
        return (listId == null || listId <= 0) ? null : todoListRepository.findOne(listId);
    }
    
    /**
     * Idによる一括検索
     * @param ids
     * @return
     */
    public List<TodoList> findAllById(HashSet<Integer> listIds){
        return (listIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findAll(listIds);
    }
    
    /**
     * タスクIDによる検索
     * @param taskIds
     * @return
     */
    public List<TodoList> findListByTaskId(HashSet<Integer> taskIds){
        return (taskIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findByIdIn(taskIds);
    }
    
    /**
     * リストIDによるリストの検索
     * @param listIds
     * @return
     */
    public List<TodoList> findListByListId(HashSet<Integer> listIds){
        return (listIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findByIdIn(listIds);
    }
    
    /**
     * タイトルによる検索
     * @param title
     * @return
     */
    public List<TodoListDto> findByTitle(String title){
        if (StringUtils.isEmpty(title))
            return Collections.emptyList();
        List<TodoList> todoLists = todoListRepository.findByTitleContainingOrderByCreated(title);
        List<TodoListDto> todoDtos = new ArrayList<TodoListDto>();
        todoLists.forEach(l->todoDtos.add(new TodoListDto(l)));
        return todoDtos;
    }
    
    /**
     * タイトルによるカウント
     * @param title
     * @return
     */
    public Integer countListByTitle(String title){
        return (StringUtils.isEmpty(title)) ? 0 : todoListRepository.countByTitleContaining(title);
    }
    
    /**
     * フロントで使用するリストのデータ取得
     * @return
     */
    public List<TodoListDto> findAllTodoListDto(){
        List<TodoListDto> todoListDtos = new ArrayList<TodoListDto>();
        List<TodoList> todoLists = findAll();
        if(todoLists.isEmpty())
            return todoListDtos;
        for(TodoList todoList : todoLists){
            TodoListDto dto = new TodoListDto(todoList);
            Integer listId = new Integer(todoList.getId());
            TodoTask todotask = todoTaskService.findTaskRelatedList(listId);
            if(todotask != null){
                dto.setTaskLimitDate(todotask.getLimitDate());
                dto.setFrontTaskLimitDate(TodoApplicationUtil.convertDateToFrontType(dto.getTaskLimitDate()));
            }
            dto.setCountAllTask(relationListTaskService.countByListId(listId));
            dto.setCountCompleteTask(todoTaskService.countCompleteTasksByListId(listId));
            todoListDtos.add(dto);
        }
        return todoListDtos;
    }
    
    /**
     * TODOリストを作成する
     * @param title
     */
    public TodoList createTodoList(String title){
        TodoList todoList = new TodoList();
        todoList.setTitle(title);
        return todoListRepository.save(todoList);
    }
    
    /**
     * バリデーションの結果
     * @param title
     * @return
     */
    public CreationResult validateListCreation(String title){
        if(StringUtils.isEmpty(title))
            return CreationResult.TITLE_EMPTY;
        if(title.codePointCount(0, title.length()) > 30)
            return CreationResult.TITLE_OUT_OF_RANGE;
        for(TodoListDto l : findByTitle(title))
            if(l.getListTitle().equals(title))
                return CreationResult.TITLE_DUOLICATION;
        return CreationResult.CREATION_SUCCESS;
    }
}
