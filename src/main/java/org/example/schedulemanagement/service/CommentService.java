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

    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));
        if (schedule.getComments().size() >= 10) {
            throw new IllegalStateException("댓글은 최대 10개까지만 작성할 수 있습니다.");
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
                        comment.getSchedule().getId().equals(scheduleId))
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
}
