package com.example.review.dto.request;

import com.example.review.entity.Todo.TodoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {

    /**
     * 수정할 제목.
     * @ex "수정된 할 일 제목"
     */
    private String title;

    /**
     * 수정할 상세 내용.
     * @ex "수정된 내용"
     */
    private String content;

    /**
     * 수정할 상태값.
     * @ex "IN_PROGRESS"
     */
    private TodoStatus status;

    /**
     * 수정할 마감일 (yyyy-MM-dd).
     * @ex "2026-05-15"
     */
    private LocalDate dueDate;

    /**
     * 수정할 우선순위. 1~5 권장.
     * @ex 5
     */
    private Integer priority;
}
