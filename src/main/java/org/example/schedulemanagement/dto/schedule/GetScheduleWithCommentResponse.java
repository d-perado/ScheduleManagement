package org.example.schedulemanagement.dto.schedule;

import lombok.Getter;
import org.example.schedulemanagement.dto.comment.CommentDTO;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleWithCommentResponse {
    private final List<CommentDTO> commentList;
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public GetScheduleWithCommentResponse(ScheduleDTO scheduleDTO, List<CommentDTO> commentDTOList) {
        this.id = scheduleDTO.getId();
        this.title = scheduleDTO.getTitle();
        this.content = scheduleDTO.getContent();
        this.writer = scheduleDTO.getWriter();
        this.createdAt = scheduleDTO.getCreatedAt();
        this.updatedAt = scheduleDTO.getUpdatedAt();
        this.commentList = commentDTOList;
    }

}
