package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Comment{
    @Id
    @Column(name = "COMMENT_ID")
    private Long id;
    private String comment;
    private String writer;
    @Column(name = "SCHEDULE_ID")
    private Long scheduleId;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Comment(String comment, String writer, Long scheduleId){
        this.comment = comment;
        this.writer = writer;
        this.scheduleId =scheduleId;
    }
}
