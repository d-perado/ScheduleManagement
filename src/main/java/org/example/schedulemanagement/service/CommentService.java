package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.comment.CreateCommentRequest;
import org.example.schedulemanagement.dto.comment.CreateCommentResponse;
import org.example.schedulemanagement.dto.comment.CommentDTO;
import org.example.schedulemanagement.dto.schedule.GetScheduleWithCommentResponse;
import org.example.schedulemanagement.dto.schedule.ScheduleDTO;
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
    private static final int MAX_COMMENT_COUNT = 10;

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        Long commentCount = commentRepository.countByScheduleId(scheduleId);

        if (commentCount >= MAX_COMMENT_COUNT) {
            throw new RuntimeException("댓글이 10개 이상입니다.");
        }

        Comment savedComment = commentRepository.save( new Comment(request.getComment(),
                request.getWriter(),
                request.getPassword(),
                schedule)
        );

        CommentDTO commentDTO = new CommentDTO(savedComment);
        return new CreateCommentResponse(commentDTO);
    }

    @Transactional(readOnly = true)
    public GetScheduleWithCommentResponse getScheduleWithComment(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정 입니다."));

        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);

        List<Comment> findCommentList = commentRepository.findByScheduleId(scheduleId);
        List<CommentDTO> commentDTOList = findCommentList.stream().map(CommentDTO::new).toList();

        return new GetScheduleWithCommentResponse(scheduleDTO, commentDTOList);

    }

}
