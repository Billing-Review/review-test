package com.example.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {
    private String title;
    private String content;
    private java.time.LocalDate dueDate;
}
