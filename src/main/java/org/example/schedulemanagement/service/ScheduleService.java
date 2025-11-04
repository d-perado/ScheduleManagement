package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.schedule.*;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 생성
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPassword());

        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    //일정 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 일정 입니다."));
        return new GetScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    //일정 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(GetScheduleByWriterRequest request) {
        List<Schedule> list = scheduleRepository.findAll().stream().toList();
        if (request.getWriter().isEmpty()) {
            return list.stream().map(schedule
                    -> new GetScheduleResponse(schedule.getScheduleId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt())).sorted(new Comparator<GetScheduleResponse>() {
                @Override
                public int compare(GetScheduleResponse o1, GetScheduleResponse o2) {
                    return o2.getUpdatedAt().compareTo(o1.getUpdatedAt());
                }
            }).toList();
        }
        ArrayList<GetScheduleResponse> userSchedules = new ArrayList<>();
        for (Schedule schedule : list) {
            if (schedule.getWriter().equals(request.getWriter())) {
                userSchedules.add(new GetScheduleResponse(
                        schedule.getScheduleId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getWriter(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()));
            }
        }
        return userSchedules.stream().sorted(new Comparator<GetScheduleResponse>() {
            @Override
            public int compare(GetScheduleResponse o1, GetScheduleResponse o2) {
                return o2.getUpdatedAt().compareTo(o1.getUpdatedAt());
            }
        }).toList();

    }

    //일정 수정
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalStateException("존재하지 않는 일정입니다."));
        if (request.getPassword().equals(schedule.getPassword())) {
            schedule.update(
                    request.getTitle(),
                    request.getWriter());
        } else {
            throw new RuntimeException("패스워드가 일치하지 않습니다.");
        }
        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                LocalDateTime.now());
    }

    //특정 일정 삭제
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
