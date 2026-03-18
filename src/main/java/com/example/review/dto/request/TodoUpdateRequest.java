package com.example.review.dto.request;

import com.example.review.entity.Todo.TodoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {
    private String title;
    private String content;
    private TodoStatus status;
}
