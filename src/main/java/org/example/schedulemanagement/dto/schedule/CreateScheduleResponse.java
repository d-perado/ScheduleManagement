package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse extends ScheduleResponse {
    public CreateScheduleResponse(Long id,
                                  String title,
                                  String content,
                                  String writer,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt
    ) {
        super(id, title, content, writer, createdAt, updatedAt);
    }
}
