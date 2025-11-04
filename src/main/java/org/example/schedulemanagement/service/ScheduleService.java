package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.schedule.*;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword());

        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String writer) {
        return scheduleRepository.findAll().stream()
                .filter(schedule -> writer.isEmpty()
                        || schedule.getWriter().equals(writer))
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getWriter(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()))
                .sorted(Comparator.comparing(GetScheduleResponse::getUpdatedAt).reversed())
                .toList();
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 일정입니다."));
        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.modify(
                    request.getTitle(),
                    request.getWriter());
        } else {
            throw new RuntimeException("패스워드가 일치하지 않습니다.");
        }
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                LocalDateTime.now());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 일정입니다."));
        if (schedule.getPassword().equals(request.getPassword())) {
            scheduleRepository.deleteById(scheduleId);
        } else {
            throw new RuntimeException("패스워드가 일치하지 않습니다.");
        }
    }
}
