package com.qa.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.repository.ListRepository;
import com.qa.todolist.dto.ListDTO;
import com.qa.todolist.exceptions.ListNotFoundException;
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
			Lists returnedList = listRepo.save(list);
			return listMapper.mapToDTO(returnedList);
		}

		public ListDTO getListByID(Integer id) {
			Optional<Lists> returnedList = listRepo.findById(id);
			if(returnedList.isPresent()) {
				return listMapper.mapToDTO(returnedList.get());
			}else {
				throw new ListNotFoundException("List ID: " + id + " was not found");
			}
		}

		public List<ListDTO> getAllLists() {
			List<Lists> lists = listRepo.findAll();
			return listMapper.mapToListDTOFromList(lists);//my own mapper that converts lists for me
		}

		public Boolean DeleteListById(Integer id) {
			if(!listRepo.existsById(id)) {
				//if dosent exist
				throw new ListNotFoundException("The list couldnt be found using ID: " + id);
			}
			listRepo.deleteById(id);//built in function dosent return anything
			//shouldn't have to delete tasks spring should do for me
			return !listRepo.existsById(id);
			
		}
		
		
}
