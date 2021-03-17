package com.qa.todolist.dto;

//simple java object
//when you request a task im not going to send the list it belongs to as well that is handled server side
//so instead only return the task information
public class TaskDTO {

	private Long id;
	
	private String task;
	
	private Boolean taskCompleted;
	
	private String createdAt;
	
	public TaskDTO() {
		super();
	}
	
	public TaskDTO(String task, Boolean taskCompleted, String createdAt) {
		super();
		this.task = task;
		this.taskCompleted = taskCompleted;
		this.createdAt = createdAt;
	}
	
	public TaskDTO(Long id, String task, Boolean taskCompleted, String createdAt) {
		super();
		this.id = id;
		this.task = task;
		this.taskCompleted = taskCompleted;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String toString() {
		return "TaskDTO [id=" + id + ", task=" + task + ", taskCompleted=" + taskCompleted + ", createdAt=" + createdAt
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TaskDTO other = (TaskDTO) obj;
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
	
	
	
	
}
