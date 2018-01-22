package com.teamlabtodolist.repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamlabtodolist.entity.TodoList;

/**
 * リストのrepository
 * @author mukaihiroto
 * 
 */
public interface TodoListRepository extends JpaRepository <TodoList, Integer> {

	List<TodoList> findByIdIn(HashSet<Integer> listIds);
	
	List<TodoList> findByTitleContainingOrderByCreated(String ListTitle);
	
	Integer countByTitleContaining(String ListTitle);
	
}