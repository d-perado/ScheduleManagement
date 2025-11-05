package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import org.example.schedulemanagement.entity.Schedule;
import java.time.LocalDateTime;

@Getter
public class ScheduleDTO {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
