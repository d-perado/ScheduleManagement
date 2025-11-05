package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.comment.CreateCommentRequest;
import org.example.schedulemanagement.dto.comment.CreateCommentResponse;
import org.example.schedulemanagement.dto.comment.CommentDTO;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.util.component.Validator;
import org.example.schedulemanagement.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private static final int MAX_COMMENT_COUNT = 10;

    private final CommentRepository commentRepository;
    private final Validator validator;


    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        int commentCount = commentRepository.countByScheduleId(scheduleId);

        validator.checkCommentCountLimit(commentCount);

        Schedule findSchedule = validator.checkExistSchedule(scheduleId);

        Comment savedComment = commentRepository.save( new Comment(request.getComment(),
                request.getWriter(),
                request.getPassword(),
                findSchedule)
        );

        CommentDTO commentDTO = new CommentDTO(savedComment);
        return new CreateCommentResponse(commentDTO);
    }


}
