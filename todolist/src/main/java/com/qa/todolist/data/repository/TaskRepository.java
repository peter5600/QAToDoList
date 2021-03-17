package com.qa.todolist.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.todolist.data.model.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer>{

	//return a list of tasks when a list id is given
	@Query(value="SELECT * FROM tasks where fk_list_id = ?1", nativeQuery=true)
	List<Task> findAllTasksFromListID(Integer id);

	//add the functions that i need in here to fetch and store the data
	//try to stick to sql queries since im more familiar
	//but if i get extra time go back and rework them to use JPQL for practice
	
}
