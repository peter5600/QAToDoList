package com.qa.todolist.data.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "list")
public class Lists {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "list_id")
	private Integer id;
	
	@NotNull
	@Column(name = "list_name", length = 30)
	private String listName;
	
	//cant have not null otherwise it fails the validation
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private String createdAt;//might have issues with this it might need to be a DateTime type 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Lists(Integer id, @NotNull String listName, String createdAt) {
		super();
		this.id = id;
		this.listName = listName;
		this.createdAt = createdAt;
	}
	

	public Lists(){
		super();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");  
		LocalDateTime now = LocalDateTime.now(); 
		setCreatedAt(dtf.format(now));//set created at myself
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listName == null) ? 0 : listName.hashCode());
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
		Lists other = (Lists) obj;
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
		if (listName == null) {
			if (other.listName != null)
				return false;
		} else if (!listName.equals(other.listName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lists [id=" + id + ", listName=" + listName + ", createdAt=" + createdAt + "]";
	}
	
	
 
	
	
	
}
