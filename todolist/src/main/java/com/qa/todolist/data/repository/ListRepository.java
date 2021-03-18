package com.qa.todolist.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.data.model.Lists;

@Repository
public interface ListRepository extends JpaRepository<Lists, Integer> {

}
