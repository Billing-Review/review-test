package com.example.review.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class TodoCreateRequest {

    /**
     * Todo 제목. 최대 100자.
     * @ex "오늘 할 일 목록 정리"
     */
    private String title;

    /**
     * Todo 상세 내용.
     * @ex "1. 장보기 2. 청소"
     */
    private String content;

    /**
     * 마감일 (yyyy-MM-dd).
     * @ex "2026-05-10"
     */
    private LocalDate dueDate;

    /**
     * 우선순위. 숫자가 높을수록 중요. 1~5 권장.
     * @ex 3
     */
    private Integer priority;

    /**
     * 태그 목록.
     * @ex ["업무", "긴급"]
     */
    private List<String> tags;
}
