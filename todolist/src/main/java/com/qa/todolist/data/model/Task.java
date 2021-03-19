package com.qa.todolist.data.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity// tell JPA and hibernate to make a table for this class
@Table(name = "tasks")//tell JPA and hibernate the table name
public class Task {

	//Define the properties of the class (Model)
	@Id//set to AI
	@GeneratedValue(strategy = GenerationType.IDENTITY)//this is a primary key
	@Column(name = "task_id")//set name to task_id
	private Integer id;
	
	//Instead of an ID like in the ERD i will implement the same but with spring functionality so instead of ID
	//that i fetch ill replace that with an object
	
	@ManyToOne(targetEntity = Lists.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_list_id")//task owns the fk so it owns the relationship
	//dont think not null is needed here i looked at the annotations they seem to 
	//be covered in the two annotations above
	private Lists list;//spring will feed the data into here as if it was an id and i was joining myself
	
	@NotNull
	@Column(name = "task", length=255)//generate varchar 255
	private String task;
	
	@NotNull
	@Column(name = "task_completed")
	private Boolean taskCompleted;
	
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private String createdAt;//might have issues with this it might need to be a DateTime type 

	
	
	//Add getters, setters, constructors, equals, toString and hashcode
	
	public Task() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");  
		LocalDateTime now = LocalDateTime.now(); 
		setCreatedAt(dtf.format(now));//set created at myself
	}



	public Task(Lists list, @NotNull String task, @NotNull Boolean taskCompleted, @NotNull String createdAt) {
		super();
		this.list = list;
		this.task = task;
		this.taskCompleted = taskCompleted;
		this.createdAt = createdAt;
	}



	public Task(Integer id, Lists list, @NotNull String task, @NotNull Boolean taskCompleted, @NotNull String createdAt) {
		super();
		this.id = id;
		this.list = list;
		this.task = task;
		this.taskCompleted = taskCompleted;
		this.createdAt = createdAt;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Lists getList() {
		return list;
	}



	public void setList(Lists list) {
		this.list = list;
	}



	public String getTask() {
		return task;
	}



	public void setTask(String task) {
		this.task = task;
	}



	public Boolean getTaskCompleted() {
		return taskCompleted;
	}



	public void setTaskCompleted(Boolean taskCompleted) {
		this.taskCompleted = taskCompleted;
	}



	public String getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		result = prime * result + ((taskCompleted == null) ? 0 : taskCompleted.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		if (taskCompleted == null) {
			if (other.taskCompleted != null)
				return false;
		} else if (!taskCompleted.equals(other.taskCompleted))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Task [id=" + id + ", list=" + list + ", task=" + task + ", taskCompleted=" + taskCompleted
				+ ", createdAt=" + createdAt + "]";
	}
	
}
