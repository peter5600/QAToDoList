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
	
	@GetMapping("/{id}")
	public ResponseEntity<ListDTO> getListByListID(@PathVariable("id") Integer id){
		ListDTO returnedList = listService.getListByID(id);
		return new ResponseEntity<ListDTO>(returnedList, HttpStatus.FOUND);//return 302 found
		
	}
	
	@GetMapping//this is just /list
	public ResponseEntity<List<ListDTO>> getListOfLists(){
		List<ListDTO> returnedLists = listService.getAllLists();
		return new ResponseEntity<List<ListDTO>>(returnedLists, HttpStatus.OK);
	}
	
	//modify tasks
	@PatchMapping("/{id}")
	public ResponseEntity<ListDTO> modifyListByID(@PathVariable("id") Integer id, @Valid @RequestBody Lists list){
		ListDTO modifiedList = listService.modifyList(id, list);
		return new ResponseEntity<ListDTO>(modifiedList, HttpStatus.OK);
	}
	
	//delete tasks
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteListById(@PathVariable("id") Integer id){
		Boolean wasDeleted = listService.DeleteListById(id);
		return new ResponseEntity<Boolean>(wasDeleted, HttpStatus.FOUND);
	}
	//add tasks
	@PostMapping
	public ResponseEntity<ListDTO> addList(@RequestBody Lists list){
		ListDTO addedList = listService.addList(list);
		return new ResponseEntity<ListDTO>(addedList, HttpStatus.CREATED);
	}
}
