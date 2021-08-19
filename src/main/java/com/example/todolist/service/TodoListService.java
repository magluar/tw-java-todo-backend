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

    public Todo updateTodoItem(Integer id, Todo doneRequest) {
        Todo todoItemDone = todoListRepository.findById(id).orElse(null);
        assert todoItemDone != null;
        return todoListRepository.save(updateTodoItemToDone(todoItemDone, doneRequest));
    }

    private Todo updateTodoItemToDone(Todo todoItemDone, Todo doneRequest) {
        todoItemDone.setDone(doneRequest.getDone());
        return todoItemDone;
    }

    public void deleteTodoItem(Integer id) {
        todoListRepository.deleteById(id);
    }
}
