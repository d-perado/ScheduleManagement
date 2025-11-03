package org.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse extends ScheduleResponse {
    public GetScheduleResponse(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, title, content, writer, createdAt, updatedAt);
    }
}
