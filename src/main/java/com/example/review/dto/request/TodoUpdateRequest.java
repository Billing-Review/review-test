package com.example.review.dto.request;

import com.example.review.entity.Todo.TodoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {

    /**
     * 변경할 Todo 제목. 미입력 시 유지.
     * @ex "수정된 할 일 제목"
     */
    private String title;

    /**
     * 변경할 상세 내용. 미입력 시 유지.
     * @ex "수정된 내용입니다."
     */
    private String content;

    /**
     * 변경할 상태값. TODO / IN_PROGRESS / DONE 중 하나.
     * @ex "IN_PROGRESS"
     */
    private TodoStatus status;

    /**
     * 변경할 마감일. ISO-8601 형식 (yyyy-MM-dd). 미입력 시 유지.
     * @ex "2026-06-30"
     */
    private LocalDate dueDate;

    /**
     * 변경할 우선순위. 1~10 사이 값 권장. 미입력 시 유지.
     * @ex 8
     */
    private Integer priority;
}
