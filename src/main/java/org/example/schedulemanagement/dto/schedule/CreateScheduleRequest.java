package org.example.schedulemanagement.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScheduleRequest{
    @NotBlank
    @Size(max = 30 )
    private String title;
    @NotBlank
    @Size(max = 200 )
    private String content;
    @NotBlank
    private String writer;
    @NotBlank
    private String password;
}
