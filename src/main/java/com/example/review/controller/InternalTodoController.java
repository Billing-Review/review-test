package com.example.review.controller;

import com.example.review.dto.request.TodoCreateRequest;
import com.example.review.dto.request.TodoUpdateRequest;
import com.example.review.dto.response.TodoResponse;
import com.example.review.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/api/todo-list")
@RequiredArgsConstructor
public class InternalTodoController {

    private final TodoService todoService;

    @GetMapping("/search")
    public List<TodoResponse> getAll() {
        return todoService.findAll();
    }

    @GetMapping("/get/{id}")
    public TodoResponse getById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    @PutMapping("/update/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        return todoService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
