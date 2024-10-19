package com.example.ToDoAppSpring.controller;

import com.example.ToDoAppSpring.controller.dto.NewTodoRequest;
import com.example.ToDoAppSpring.document.ToDo;
import com.example.ToDoAppSpring.repository.ToDoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping(value = "/newtodo/{id}")
    public ResponseEntity<Void> newTodo(@PathVariable String id, @RequestBody NewTodoRequest request) {
        System.out.println(request);
        ToDo todo = new ToDo(request.title(), id);
        toDoRepository.insert(todo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updatetodos/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable String id, @RequestBody ToDo updatedToDo) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        todo.setStatus(updatedToDo.getStatus());
        todo.setTitle(updatedToDo.getTitle());

        ToDo newToDo = toDoRepository.save(todo);

        return ResponseEntity.ok(newToDo);
    }

    @GetMapping("/gettodos/{userId}")
    public ResponseEntity<List<ToDo>> getToDosByUser(@PathVariable String userId) {
        List<ToDo> todos = toDoRepository.findByUserId(userId);
        if (todos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todos);

    }

    @DeleteMapping("/deletetodo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id){
        toDoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}