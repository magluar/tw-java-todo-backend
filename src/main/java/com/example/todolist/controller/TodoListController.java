package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/todos")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoListService.getAllTodos();
    }

    @PostMapping
    public Todo addTodoItem(@RequestBody Todo todo) {
        return todoListService.addToDoItem(todo);
    }

    @PutMapping(path = "/{id}")
    public Todo updateTodoItem(@PathVariable Integer id, @RequestBody Todo doneRequest){
        return todoListService.updateTodoItem(id, doneRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTodoItem(@PathVariable Integer id){
        todoListService.deleteTodoItem(id);
    }
}
