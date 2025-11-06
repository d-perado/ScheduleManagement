package org.example.schedulemanagement.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateScheduleRequest {
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    @Size(max = 30, message = "제목은 최대 30자입니다.")
    private String title;
    @NotBlank(message = "작성자명은 비어있을 수 없습니다.")
    private String writer;
    @NotBlank(message = "패스워드는 비어있을 수 없습니다.")
    private String password;
}
