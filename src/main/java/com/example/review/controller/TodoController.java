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
@RequestMapping("/api/todo-list")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /**
     * Todo 단건 조회
     * @apiScope internal
     *
     * 지정한 ID의 Todo 항목을 반환합니다.
     * 존재하지 않는 ID 요청 시 404를 반환합니다.
     *
     * @param id 조회할 Todo의 고유 식별자
     * @return 조회된 Todo 정보
     */
    @GetMapping("/{id}")
    public TodoResponse getById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    /**
     * Todo 생성
     * @apiScope internal
     *
     * 새로운 Todo 항목을 생성합니다.
     * 생성된 항목의 초기 상태는 TODO입니다.
     *
     * @param request 생성할 Todo 정보 (title 필수)
     * @return 생성된 Todo 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    /**
     * Todo 전체 수정
     * @apiScope internal
     *
     * 지정한 ID의 Todo 항목을 전달된 값으로 전체 교체합니다.
     *
     * @param id 수정할 Todo의 고유 식별자
     * @param request 수정할 내용 (전체 필드 교체)
     * @return 수정된 Todo 정보
     */
    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        return todoService.update(id, request);
    }
}
