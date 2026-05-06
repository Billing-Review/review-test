package com.example.review.controller;

import com.example.review.dto.request.TodoCreateRequest;
import com.example.review.dto.request.TodoUpdateRequest;
import com.example.review.dto.response.TodoResponse;
import com.example.review.entity.Todo.TodoStatus;
import com.example.review.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/external/api/todo-list")
@RequiredArgsConstructor
public class ExternalTodoController {

    private final TodoService todoService;

    /**
     * Todo 단건 조회
     * docUrl: external
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
     * Todo 통계 조회
     * docUrl: external
     *
     * 전체 또는 필터링된 Todo의 상태별 집계를 반환합니다.
     * includeDueToday 옵션 사용 시 오늘 마감 항목 수도 제공합니다.
     *
     * @param status 필터링할 상태값 (TODO / IN_PROGRESS / DONE), 미입력 시 전체
     * @param minPriority 이 값 이상의 우선순위를 가진 항목만 집계, 미입력 시 전체
     * @param includeDueToday true 전달 시 응답에 dueToday 필드 포함, 기본값 false
     * @return 상태별 Todo 집계 (total, done, pending, dueToday(조건부))
     */
    @GetMapping("/statistics")
    public Map<String, Long> getStatistics(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(required = false) Integer minPriority,
            @RequestParam(required = false, defaultValue = "false") boolean includeDueToday) {
        List<TodoResponse> targets = (status != null)
                ? todoService.findAllByStatus(status)
                : todoService.findAll();
        if (minPriority != null) {
            targets = targets.stream()
                    .filter(t -> t.getPriority() != null && t.getPriority() >= minPriority)
                    .toList();
        }
        long total = targets.size();
        long done = targets.stream().filter(t -> t.getStatus() == TodoStatus.DONE).count();
        long dueToday = includeDueToday
                ? targets.stream().filter(t -> LocalDate.now().equals(t.getDueDate())).count()
                : 0L;
        return includeDueToday
                ? Map.of("total", total, "done", done, "pending", total - done, "dueToday", dueToday)
                : Map.of("total", total, "done", done, "pending", total - done);
    }

    /**
     * Todo 생성
     * docUrl: external
     *
     * 새로운 Todo 항목을 생성합니다.
     * 생성된 항목의 초기 상태는 TODO입니다.
     *
     * @param request 생성할 Todo 정보 (title 필수)
     * @return 생성된 Todo 정보
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse create(@RequestBody TodoCreateRequest request) {
        return todoService.create(request);
    }

    /**
     * Todo 수정
     * docUrl: external
     *
     * 지정한 ID의 Todo 항목을 수정합니다.
     * fields 파라미터로 수정할 필드를 한정할 수 있으며, 미입력 시 전체 필드를 덮어씁니다.
     *
     * @param id 수정할 Todo의 고유 식별자
     * @param request 수정할 내용
     * @param fields 수정할 필드명 목록 (예: title,status), 미입력 시 전체 수정
     * @return 수정된 Todo 정보
     */
    @PatchMapping("/{id}")
    public TodoResponse update(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request,
            @RequestParam(required = false) List<String> fields) {
        return todoService.update(id, request, fields);
    }

    /**
     * Todo 삭제
     * docUrl: external
     *
     * 지정한 ID의 Todo 항목을 영구 삭제합니다.
     * 삭제된 항목은 복구할 수 없습니다.
     *
     * @param id 삭제할 Todo의 고유 식별자
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
