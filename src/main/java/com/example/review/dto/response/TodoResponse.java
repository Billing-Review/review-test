package com.example.review.dto.response;

import com.example.review.entity.Todo;
import com.example.review.entity.Todo.TodoStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class TodoResponse {

    /** Todo 고유 식별자. @ex 42 */
    private Long id;

    /** Todo 제목. @ex "오늘 할 일 목록 정리" */
    private String title;

    /** Todo 상세 내용. @ex "1. 장보기 2. 청소" */
    private String content;

    /** 현재 상태 (TODO / IN_PROGRESS / DONE). @ex "TODO" */
    private TodoStatus status;

    /** 마감일 (yyyy-MM-dd). @ex "2026-05-10" */
    private LocalDate dueDate;

    /** 우선순위 (1~5). @ex 3 */
    private Integer priority;

    /** 생성 일시. @ex "2026-05-01T10:00:00" */
    private LocalDateTime createdAt;

    /** 최종 수정 일시. @ex "2026-05-06T15:30:00" */
    private LocalDateTime updatedAt;

    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .status(todo.getStatus())
                .dueDate(todo.getDueDate())
                .priority(todo.getPriority())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }
}
