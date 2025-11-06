package org.example.schedulemanagement.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //@NoArgsConstructor(기본 생성자) 를 Request DTO 클래스에 꼭 붙여야 하는 이유는 객체를
// JSON → Java 객체로 변환할 때(Spring이 내부적으로) 반드시 기본 생성자가 필요하기 때문입니다
public class CreateScheduleRequest {
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    @Size(max = 30, message = "제목은 최대 30자입니다.")
    private String title;
    @NotBlank(message = "일정내용은 비어있을 수 없습니다.")
    @Size(max = 200, message = "일정 내용은 최대 200자입니다.")
    private String content;
    @NotBlank(message = "작성자명은 비어있을 수 없습니다.")
    private String writer;
    @NotBlank(message = "패스워드는 비어있을 수 없습니다.")
    private String password;
}
