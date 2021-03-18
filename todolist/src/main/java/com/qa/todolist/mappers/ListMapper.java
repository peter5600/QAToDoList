package com.qa.todolist.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.dto.ListDTO;

@Component
public class ListMapper {

private ModelMapper modelMapper;
	
	@Autowired//spring will inject the value for me
	public ListMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ListDTO mapToDTO(Lists list) {
		return this.modelMapper.map(list, ListDTO.class);
	}
	
	public Lists mapToList(ListDTO listDTO) {
		return this.modelMapper.map(listDTO, Lists.class);
	}
	
	public List<ListDTO> mapToListDTOFromList(List<Lists> list){
		List<ListDTO> lists = new ArrayList<ListDTO>();
		list.stream().forEach((l) -> lists.add(mapToDTO(l)));
		return lists;
	}
}
