package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.schedule.*;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.exception.InvalidPasswordException;
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

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        //논리순서로 작성하기
        //띄어쓰기
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
    public List<GetScheduleResponse> getAllSchedules(String writer) {
        return scheduleRepository.findAll()
                .stream()
                .filter(schedule -> writer.isEmpty()
                        || schedule.getWriter().equals(writer))
                .map(schedule -> new GetScheduleResponse(new ScheduleDTO(schedule)))
                .sorted(Comparator.comparing(GetScheduleResponse::getUpdatedAt).reversed())
                .toList();
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다."));

        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new InvalidPasswordException();
        }

        schedule.modify(request.getTitle(), request.getWriter());

        ScheduleDTO scheduleDTO = new ScheduleDTO(schedule);
        return new UpdateScheduleResponse(scheduleDTO);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest request) {
        Schedule findSchedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 일정입니다."));

        if (!findSchedule.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException();
        }

        commentRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.deleteById(scheduleId);

    }
}
