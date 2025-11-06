package org.example.schedulemanagement.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "작성자는 비어있을 수 없습니다.")
    @Size(max = 100, message = "작성자의 이름은 100자 이내여야 합니다.")
    private String writer;
    @NotBlank(message = "패스워드는 비어있을 수 없습니다.")
    private String password;
    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
    private String comment;
}
