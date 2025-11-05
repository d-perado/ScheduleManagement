package org.example.schedulemanagement.dto.comment;

import lombok.Getter;
import org.example.schedulemanagement.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentDTO {
    private final Long id;
    private final String comment;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.writer = comment.getWriter();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
