package org.example.schedulemanagement.dto.comment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulemanagement.entity.Schedule;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    @NotBlank
    @Size(max = 100 )
    private String comment;
    @NotBlank
    private String writer;
    @NotBlank
    private String password;
}
