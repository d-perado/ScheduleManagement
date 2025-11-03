package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateScheduleRequest {
    private String title;
    private String writer;
    private String password;
}
