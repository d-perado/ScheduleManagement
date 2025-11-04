package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String title;
    @Column(nullable = false, length = 200)
    private String content;
    @Column(nullable = false, length = 20)
    private String writer;
    @Column(nullable = false)
    private String password;


    @Builder
    public Schedule(String title, String content, String writer, String password) {
        super();
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public void modify(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }

}
