package org.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class ScheduleRequest {
    private String title;
    private String content;
    private String writer;
}
