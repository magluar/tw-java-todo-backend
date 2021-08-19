package com.example.todolist.integration;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoListRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoListRepository todoListRepository;

    @BeforeEach
    void deleteAll(){
        todoListRepository.deleteAll();
    }

    @Test
    void should_return_all_todo_items_when_call_getAllTodos_api() throws Exception {
        //given
        final Todo todo = new Todo("test text", false);
        todoListRepository.save(todo);

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(todo.getId()))
                .andExpect(jsonPath("$[0].text").value("test text"))
                .andExpect(jsonPath("$[0].done").value("false"));
    }

    @Test
    void should_return_added_todo_item_when_call_addTodoItem_api() throws Exception {
        //given
        String todoItem = "{\n" +
                "    \"text\": \"random text haha\",\n" +
                "    \"done\": false\n" +
                "}";

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("random text haha"))
                .andExpect(jsonPath("$.done").value("false"));
    }

    @Test
    void should_return_updated_todo_item_when_call_updateTodoItem_api() throws Exception {
        //given
        final Todo todo = new Todo("abcd1234", false);
        String updatedTodoItem = "{\n" +
                "    \"done\": true\n" +
                "}";

        //when

        //then
        int id = todoListRepository.save(todo).getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(updatedTodoItem))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value("abcd1234"))
                .andExpect(jsonPath("$.done").value("true"));
    }
}