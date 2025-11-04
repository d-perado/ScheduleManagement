package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import org.example.schedulemanagement.entity.Comment;

import java.util.List;

@Getter
public class GetCommentListByScheduleResponse {
    private final Long id;
    private final List<Comment> commentList;

    public GetCommentListByScheduleResponse(Long id, List<Comment> commentList) {
        this.id = id;
        this.commentList = commentList;
    }
}
