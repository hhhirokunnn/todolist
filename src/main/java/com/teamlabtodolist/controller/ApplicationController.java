package com.teamlabtodolist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teamlabtodolist.constraints.CreationResult;
import com.teamlabtodolist.constraints.TaskStatus;
import com.teamlabtodolist.dto.TodoTaskDto;
import com.teamlabtodolist.entity.TodoList;
import com.teamlabtodolist.entity.TodoTask;
import com.teamlabtodolist.service.RelationListTaskService;
import com.teamlabtodolist.service.TodoListService;
import com.teamlabtodolist.service.TodoTaskService;

/**
 * 画面表示を行うためのコントローラ
 * @author mukaihiroto
 * 
 */
@Controller
public class ApplicationController {

    @Autowired
    private TodoListService todoListService;
    
    @Autowired
    private TodoTaskService todoTaskService;
    
    @Autowired
    private RelationListTaskService relationListTaskService;
    
    /**
     * トップ画面
     * @param model
     * @return
     */
    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    String index(Model model, String creationCondition) {
        model.addAttribute("todoListDtos", todoListService.findAllTodoListDto());
        return "/lists";
    }
    
    /**
     * タスク詳細画面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{listId}/tasks/", method = RequestMethod.GET)
    String searchTaskByListId(@PathVariable("listId") Integer listId,Model model,String creationCondition) {
        TodoList todoList = todoListService.searchById(listId);
        if(todoList == null)
            return "redirect:/404.html";
        model.addAttribute("todoList",todoList);
        List<TodoTaskDto> todoTaskDto = todoTaskService.getTaskDtos(listId);
        model.addAttribute("todoTasks", todoTaskDto);
        return "/tasks";
    }
    
    /**
     * 検索画面
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    String searchByTitle() {
        return "/search";
    }
    
    /**
     * リストを作成
     * @param model
     * @param title
     * @return
     */
    @RequestMapping(value = "/list/create", method = RequestMethod.POST)
    String createTodoList(RedirectAttributes redirectAttributes, @RequestParam("title")String title) {
        //バリデーション
        CreationResult creationResult = todoListService.validateListCreation(title);
        redirectAttributes.addFlashAttribute("creationResult", creationResult.getResultMessage());
        if(CreationResult.CREATION_SUCCESS == creationResult)
            todoListService.createTodoList(title);
        return "redirect:/lists";
    }
    
    /**
     * TODOタスクを作成
     * @param id
     * @param form
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(value = "/task/create", method = RequestMethod.POST)
    String createTodoTask(@RequestParam("listId") Integer listId,
            @RequestParam("title")String title,
            @RequestParam("limitDate")String limitDate,
            RedirectAttributes redirectAttributes,
            Model model) {
        TodoTaskDto todoTaskDto = new TodoTaskDto();
        todoTaskDto.setLimitDateFromFront(limitDate);
        //statusCdに初期値を入れる
        todoTaskDto.setStatusCd(TaskStatus.NOT_YET.getStatusCd());
        todoTaskDto.setTaskTitle(title);
        //バリデーション
        List<CreationResult> creationResults = todoTaskService.validateTaskCreation(todoTaskDto);
        List<String> resultMessages = new ArrayList<String>();
        creationResults.forEach(cR -> resultMessages.add(cR.getResultMessage()));
        redirectAttributes.addFlashAttribute("creationResult", resultMessages);
        for(CreationResult creationResult : creationResults)
            if(creationResult.equals(CreationResult.CREATION_SUCCESS))
            return "redirect:/list/"+listId+"/tasks/";
        //タスクの作成
        TodoTask addedTask = todoTaskService.createTodoTask(todoTaskDto);
        //リストとタスクの紐付け作成
        relationListTaskService.createRelation(listId, addedTask.getId());
        model.addAttribute("todoList",todoListService.searchById(listId));
        model.addAttribute("todoTasks", todoTaskService.getTaskDtos(listId));
        return "redirect:/list/"+listId+"/tasks/";
    }
    
    /**
     * タスクの更新
     * @param id
     * @param taskId
     * @param model
     * @return
     */
    @RequestMapping(value = "/task/{taskId}/update", method = RequestMethod.POST)
    String updateTodoTask(@PathVariable("taskId") Integer taskId,
            @RequestParam("listId") Integer listId) {
        todoTaskService.updateStatusCd(taskId);
        return "redirect:/list/"+listId+"/tasks/";
    }
    
    /**
     * タスク削除
     * @param taskId
     * @param listId
     * @param model
     * @return
     */
    @RequestMapping(value = "/task/{taskId}/delete", method = RequestMethod.POST)
    String deleteTodoTask(@PathVariable("taskId") Integer taskId,
            @RequestParam("listId") Integer listId) {
        todoTaskService.deleteTodoTask(listId, taskId);
        return "redirect:/list/"+listId+"/tasks/";
    }
    
}