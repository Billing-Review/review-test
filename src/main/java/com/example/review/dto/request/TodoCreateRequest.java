package com.example.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {
    private String title;
    private String content;
    private LocalDate dueDate;
    private Integer priority;
}
