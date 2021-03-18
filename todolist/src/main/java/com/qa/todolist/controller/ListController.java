package com.qa.todolist.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolist.data.model.Lists;	
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.dto.TaskDTO;
import com.qa.todolist.services.ListService;



@RestController
@RequestMapping(path = "/list")
@CrossOrigin 
public class ListController {

private ListService listService;
	
	@Autowired
	public ListController(ListService listService) {
		this.listService = listService;
	}
	
	@GetMapping("/lists/{id}")
	public ResponseEntity<List<ListDTO>> getListByListID(@PathVariable("id") Integer id){
		return null;
		
	}
	
	//modify tasks
	@PatchMapping("/{id}")
	public ResponseEntity<ListDTO> modifyListByID(@PathVariable("id") Integer id, @Valid @RequestBody Lists list){
		return null;
	}
	
	//delete tasks
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteListById(@PathVariable("id") Integer id){
		return null;
	}
	//add tasks
	@PostMapping
	public ResponseEntity<ListDTO> addList(@RequestBody Lists list){
		System.out.println("---- list:" +list.toString());
		ListDTO addedList = listService.addList(list);
		return new ResponseEntity<ListDTO>(addedList, HttpStatus.CREATED);
	}
}
