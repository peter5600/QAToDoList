package com.qa.todolist.models;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.qa.todolist.data.model.Lists;
public class ListModelTest {

	//test the diffrent controllers
	@Test
	public void isOfTypeTest() {
		assertTrue(new Lists() instanceof Lists);
	}
	
	@Test
	public void isOfTypeStringNameConstructorTest() {
		assertTrue(new Lists("my list name") instanceof Lists);
	}
	
	
	
	
	
	
	
}
