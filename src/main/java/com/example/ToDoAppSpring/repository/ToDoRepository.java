package com.example.ToDoAppSpring.repository;

import com.example.ToDoAppSpring.document.ToDo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
    List<ToDo> findByUserId(String userId);
}
