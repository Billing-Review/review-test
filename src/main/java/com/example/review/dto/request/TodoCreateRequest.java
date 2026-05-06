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
     * @ex "1. 코드 리뷰\n2. 문서 작성\n3. 배포 확인"
     */
    private String content;

    /**
     * 마감일. ISO-8601 형식 (yyyy-MM-dd). 미입력 시 null.
     * @ex "2026-05-31"
     */
    private LocalDate dueDate;

    /**
     * 우선순위. 숫자가 높을수록 중요. 1~10 사이 값 권장.
     * @ex 5
     */
    private Integer priority;

    /**
     * 태그 목록. 분류 및 검색에 활용.
     * @ex ["업무", "긴급"]
     */
    private List<String> tags;
}
