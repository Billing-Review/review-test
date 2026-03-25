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
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public TodoResponse getById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        return todoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
