package com.example.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {
    private String title;
    private String content;
    private LocalDate dueDate;
    private Integer priority;
    private List<String> tags;
}
