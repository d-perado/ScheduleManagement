package org.example.schedulemanagement.dto.comment;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long scheduleId;
    private final Long id;
    private final String comment;
    private final String writer;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;

    public CreateCommentResponse(Long scheduleId,
                                 Long id,
                                 String comment,
                                 String writer,
                                 LocalDateTime createAt,
                                 LocalDateTime updatedAt
    ) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.comment = comment;
        this.writer = writer;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }
}
