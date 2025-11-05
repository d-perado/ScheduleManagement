package org.example.schedulemanagement.dto.comment;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String comment;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateCommentResponse(CommentDTO commentDTO) {
        this.id = commentDTO.getId();
        this.comment = commentDTO.getComment();
        this.writer = commentDTO.getWriter();
        this.createdAt = commentDTO.getCreatedAt();
        this.updatedAt = commentDTO.getUpdatedAt();
    }
}
