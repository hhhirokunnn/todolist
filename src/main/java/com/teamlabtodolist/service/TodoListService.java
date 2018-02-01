package com.teamlabtodolist.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * エスケープする文字のリスト
     */
    public static final Map<String, String> ESCAPE_SEQUENCE = new HashMap<String,String>(){{
        put("&", "&amp;");
        put("\"", "&quot;");
        put("<", "&lt;");
        put(">", "&gt;");
        put("'", "&#39;");
        }};
    
    /**
     * 全件検索
     * @return
     */
    public List<TodoList> findAll(){
        try{
            return todoListRepository.findAll();
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * Idによる検索
     * @param id
     * @return
     */
    public TodoList searchById(Integer listId){
        try{
            return (listId == null || listId <= 0) ? null : todoListRepository.findOne(listId);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * Idによる一括検索
     * @param ids
     * @return
     */
    public List<TodoList> findAllById(HashSet<Integer> listIds){
        try{
            return (listIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findAll(listIds);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * タスクIDによる検索
     * @param taskIds
     * @return
     */
    public List<TodoList> findListByTaskId(HashSet<Integer> taskIds){
        try{
            return (taskIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findByIdIn(taskIds);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * リストIDによるリストの検索
     * @param listIds
     * @return
     */
    public List<TodoList> findListByListId(HashSet<Integer> listIds){
        try{
            return (listIds.isEmpty()) ? Collections.emptyList() : todoListRepository.findByIdIn(listIds);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    /**
     * タイトルによる検索
     * @param title
     * @return
     */
    public List<TodoListDto> findByTitle(String title){
        if (StringUtils.isEmpty(title))
            return Collections.emptyList();
        List<TodoList> todoLists = new ArrayList<TodoList>();
        try{
            todoLists = todoListRepository.findByTitleContainingOrderByCreated(title);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
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
        try{
            return (StringUtils.isEmpty(title)) ? 0 : todoListRepository.countByTitleContaining(title);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
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
        if(StringUtils.isEmpty(title) || title.length() > 30)
            return null;
        for(TodoListDto l : findByTitle(title))
            if(l.getListTitle().equals(title))
                return null;
        String result = title;
        //入力文字をエスケープ
        for(Map.Entry<String, String> target : ESCAPE_SEQUENCE.entrySet())
            result = title.replace(target.getKey(), target.getValue());
        TodoList todoList = new TodoList();
        todoList.setTitle(result);
        try{
            return todoListRepository.save(todoList);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
