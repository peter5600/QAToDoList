package com.qa.todolist.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.qa.todolist.data.model.Lists;
import com.qa.todolist.data.model.Task;

public class TaskModelTest {

	@Test
	public void isOfTypeTest() {
		assertTrue(new Task(new Lists("list name"), "this is my task", false, "19-03-2021 09:58") instanceof Task);
	}
}
