package com.teamlabtodolist.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.teamlabtodolist.dto.TodoListDto;
import com.teamlabtodolist.dto.TodoTaskDto;
import com.teamlabtodolist.entity.TodoList;
import com.teamlabtodolist.entity.TodoTask;
import com.teamlabtodolist.service.TodoListService;
import com.teamlabtodolist.service.TodoTaskService;

/**
 * APIのコントローラ
 * @author mukaihiroto
 * 
 */
@RestController
@RequestMapping("/api/v1/")
public class ApplicationApiController {
	
	@Autowired
	private TodoListService todoListService;
	
	@Autowired
	private TodoTaskService todoTaskService;
	
	/**
	 * タスクのキーワード検索API
	 * @param title
	 * @return
	 */
	@GetMapping(path="task/freeword")
	@ResponseBody
    public List<TodoTaskDto> getTaskByTitle(@RequestParam("title") String title) {
        return todoTaskService.searchTaskByTitle(title);
    }
	
	/**
	 * リストのキーワード検索API
	 * @param title
	 * @return
	 */
	@GetMapping(path="list/freeword")
	@ResponseBody
    public List<TodoListDto> getListByTitle(@RequestParam("title") String title) {
        return todoListService.findByTitle(title);
    }

}