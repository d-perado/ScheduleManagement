package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.comment.CreateCommentRequest;
import org.example.schedulemanagement.dto.comment.CreateCommentResponse;
import org.example.schedulemanagement.dto.comment.CommentResponse;
import org.example.schedulemanagement.dto.schedule.GetScheduleWithCommentResponse;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.repository.CommentRepository;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    private static final int MAX_COMMENT_COUNT = 10;

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        List<Comment> comments = commentRepository
                .findAll().stream()
                .filter(comment -> comment.getSchedule().getId().equals(schedule.getId())).toList();
        if(comments.size() >= MAX_COMMENT_COUNT){
            throw new RuntimeException("댓글이 10개 이상입니다.");
        }

        Comment savedComment = commentRepository.save( new Comment(request.getComment(),
                request.getWriter(),
                request.getPassword(),
                schedule
        ));

        return new CreateCommentResponse(
                savedComment.getSchedule().getId(),
                savedComment.getId(),
                savedComment.getComment(),
                savedComment.getWriter(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleWithCommentResponse getScheduleWithComment(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 일정 입니다."));
        List<CommentResponse> commentList = commentRepository.findAll()
                .stream()
                .filter(comment ->
                        comment.getSchedule().getId().equals(schedule.getId()))
                .map(comment ->
                        new CommentResponse(comment.getId(),
                                comment.getComment(),
                                comment.getWriter(),
                                comment.getCreatedAt(),
                                comment.getUpdatedAt())
                ).toList();

        return new GetScheduleWithCommentResponse(schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                commentList);
    }

    @Transactional
    public void deleteCommentsByScheduleId(Long scheduleId) {
        commentRepository.deleteByScheduleId(scheduleId);
    }
}
