package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScheduleRequest{
    private String title;
    private String content;
    private String writer;
    private String password;
}
