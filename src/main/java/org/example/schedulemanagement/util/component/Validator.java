package org.example.schedulemanagement.util.component;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.util.exception.CustomException;
import org.example.schedulemanagement.util.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {
    private final ScheduleRepository scheduleRepository;

    public void passwordValidate(String storedPassword, String inputPassword) {
        if (!storedPassword.equals(inputPassword)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }

    public Schedule checkExistSchedule(Long scheduleId){
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
