package com.qa.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.repository.ListRepository;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.mappers.ListMapper;


@Service
public class ListService {
	
	//this lets me interact with the db
		private ListRepository listRepo;
		
		//lets me map task to taskdto and vice versa 
		private ListMapper listMapper;
		
		@Autowired//spring inject values in for me
		public ListService(ListRepository listRepo, ListMapper listMapper) {
			this.listRepo = listRepo;
			this.listMapper = listMapper;
		}

		public ListDTO addList(Lists list) {
			System.out.println(list.toString());
			Lists returnedList = listRepo.save(list);
			return listMapper.mapToDTO(returnedList);
		}
		
		
}
