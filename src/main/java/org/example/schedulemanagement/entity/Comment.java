package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String comment;
    @Column(nullable = false, length = 30)
    private String writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;
    @Column(nullable = false)
    private String password;

    @Builder
    public Comment(String comment, String writer, String password, Schedule schedule) {
        this.comment = comment;
        this.writer = writer;
        this.password = password;
        this.schedule = schedule;
    }

}
