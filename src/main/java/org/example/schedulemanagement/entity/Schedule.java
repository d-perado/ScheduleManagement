package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends TimeAt{

    @Id
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


    @Builder
    public Schedule(String title, String content, String writer, String password) {
        super();
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
