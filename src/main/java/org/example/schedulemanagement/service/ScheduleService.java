package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.comment.CommentDTO;
import org.example.schedulemanagement.dto.schedule.*;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.util.component.Validator;
import org.example.schedulemanagement.repository.CommentRepository;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final Validator validator;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {

        Schedule newSchedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword());

        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO(savedSchedule);

        return new CreateScheduleResponse(scheduleDTO);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedulesByWriter(String writer) {
        List<Schedule> findScheduleList = writer.isEmpty() ? scheduleRepository.findAll() : scheduleRepository.findByWriter(writer);

        return findScheduleList.stream()
                .map(schedule -> new GetScheduleResponse(new ScheduleDTO(schedule)))
                .sorted(Comparator.comparing(GetScheduleResponse::getUpdatedAt).reversed())
                .toList();
    }


    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule findSchedule = validator.checkExistSchedule(scheduleId);
        //패스워드 확인
        validator.passwordValidate(findSchedule.getPassword(), request.getPassword());

        findSchedule.modify(request.getTitle(), request.getWriter());

        ScheduleDTO scheduleDTO = new ScheduleDTO(findSchedule);
        return new UpdateScheduleResponse(scheduleDTO);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest request) {
        Schedule findSchedule = validator.checkExistSchedule(scheduleId);
        //패스워드 확인
        validator.passwordValidate(findSchedule.getPassword(), request.getPassword());

        commentRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);

    }

    @Transactional(readOnly = true)
    public GetScheduleWithCommentResponse getScheduleWithComment(Long scheduleId) {
        Schedule findSchedule = validator.checkExistSchedule(scheduleId);

        ScheduleDTO scheduleDTO = new ScheduleDTO(findSchedule);

        List<Comment> findCommentList = commentRepository.findByScheduleId(scheduleId);
        List<CommentDTO> commentDTOList = findCommentList.stream().map(CommentDTO::new).toList();

        return new GetScheduleWithCommentResponse(scheduleDTO, commentDTOList);

    }

}
