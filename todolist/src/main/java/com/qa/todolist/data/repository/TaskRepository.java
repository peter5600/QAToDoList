package com.qa.todolist.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.data.model.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer>{

	//add the functions that i need in here to fetch and store the data
	//try to stick to sql queries since im more familiar
	//but if i get extra time go back and rework them to use JPQL for practice
	
}
