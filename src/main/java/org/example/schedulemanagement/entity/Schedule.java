package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Schedule{

    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 200)
    private String content;
    @Column(nullable = false, length = 20)
    private String writer;
    @Column(nullable = false, length = 50)
    private String password;
    @OneToMany
    @JoinColumn(name = "SCHEDULE_ID")
    private List<Comment> comments = new ArrayList<>();
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Builder
    public Schedule(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public void update(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }
}
