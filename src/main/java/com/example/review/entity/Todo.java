package com.example.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TodoStatus status = TodoStatus.TODO;

    private LocalDate dueDate;

    @Column
    private Integer priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum TodoStatus {
        TODO, IN_PROGRESS, DONE
    }

    public void update(String title, String content, LocalDate dueDate, Integer priority) {
        if (title != null) this.title = title;
        if (content != null) this.content = content;
        if (dueDate != null) this.dueDate = dueDate;
        if (priority != null) this.priority = priority;
    }

    public void updateStatus(TodoStatus status) {
        this.status = status;
    }
}
