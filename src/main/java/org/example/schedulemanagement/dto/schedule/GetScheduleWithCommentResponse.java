package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import org.example.schedulemanagement.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleWithCommentResponse extends ScheduleResponse {

    List<CommentResponse> commentList;

    public GetScheduleWithCommentResponse(Long id, String title, String content,
                                          String writer, LocalDateTime createdAt,
                                          LocalDateTime updatedAt, List<CommentResponse> commentList) {
        super(id, title, content, writer, createdAt, updatedAt);
        this.commentList = commentList;
    }

}
