package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoListRepository;
import com.example.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoListService.getAllTodos();
    }
}
