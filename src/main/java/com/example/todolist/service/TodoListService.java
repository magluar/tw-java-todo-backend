package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    public List<Todo> getAllTodos() {
        return todoListRepository.findAll();
    }

    public Todo addToDoItem(Todo todo) {
        return todoListRepository.save(todo);
    }
}
