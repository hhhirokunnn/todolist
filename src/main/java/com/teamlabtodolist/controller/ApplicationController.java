package com.teamlabtodolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    String index(Model model) {
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
    String searchTaskByListId(@PathVariable("listId") Integer listId,Model model) {
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
    String createTodoList(@RequestParam("title")String title, Model model) {
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
	@RequestMapping(value = "/task/create", method = RequestMethod.POST)
    String createTodoTask(@RequestParam("listId") Integer listId,
    		@RequestParam("title")String title,
    		@RequestParam("limitDate")String limitDate,
    		Model model) {
		TodoTaskDto todoTaskDto = new TodoTaskDto();
        todoTaskDto.setLimitDateFromFront(limitDate);
		//statusCdは初期値1
        todoTaskDto.setStatusCd("1");
        todoTaskDto.setTaskTitle(title);
		//タスクの作成
		TodoTask addedTask = todoTaskService.createTodoTask(todoTaskDto);
		//タイトルがバリデーションエラーの場合nullが返ってくる
		if(addedTask == null)
			return "redirect:/list/"+listId+"/tasks/";
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