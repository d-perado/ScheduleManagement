package org.example.schedulemanagement.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.schedulemanagement.entity.Schedule;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private String comment;
    private String writer;
    private String password;
}
