package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public abstract class ScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    protected ScheduleResponse(Long id,
                               String title,
                               String content,
                               String writer,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
