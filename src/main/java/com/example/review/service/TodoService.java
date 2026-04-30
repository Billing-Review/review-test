package com.example.review.service;

import com.example.review.dto.request.TodoCreateRequest;
import com.example.review.dto.request.TodoUpdateRequest;
import com.example.review.dto.response.TodoResponse;
import com.example.review.entity.Todo;
import com.example.review.entity.Todo.TodoStatus;
import com.example.review.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResponse::from)
                .toList();
    }

    public List<TodoResponse> findAllByStatus(TodoStatus status) {
        return todoRepository.findAll().stream()
                .filter(t -> t.getStatus() == status)
                .map(TodoResponse::from)
                .toList();
    }

    public TodoResponse findById(Long id) {
        return TodoResponse.from(getTodo(id));
    }

    @Transactional
    public TodoResponse create(TodoCreateRequest request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dueDate(request.getDueDate())
                .priority(request.getPriority())
                .build();
        return TodoResponse.from(todoRepository.save(todo));
    }

    @Transactional
    public TodoResponse update(Long id, TodoUpdateRequest request) {
        return update(id, request, null);
    }

    @Transactional
    public TodoResponse update(Long id, TodoUpdateRequest request, List<String> fields) {
        Todo todo = getTodo(id);
        boolean updateAll = fields == null || fields.isEmpty();
        todo.update(
                updateAll || fields.contains("title") ? request.getTitle() : null,
                updateAll || fields.contains("content") ? request.getContent() : null,
                updateAll || fields.contains("dueDate") ? request.getDueDate() : null,
                updateAll || fields.contains("priority") ? request.getPriority() : null
        );
        if (request.getStatus() != null && (updateAll || fields.contains("status"))) {
            todo.updateStatus(request.getStatus());
        }
        return TodoResponse.from(todo);
    }

    @Transactional
    public void delete(Long id) {
        todoRepository.delete(getTodo(id));
    }

    private Todo getTodo(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
    }
}
