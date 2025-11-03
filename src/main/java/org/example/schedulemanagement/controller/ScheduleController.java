package org.example.schedulemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.CreateScheduleRequest;
import org.example.schedulemanagement.dto.CreateScheduleResponse;
import org.example.schedulemanagement.dto.GetScheduleResponse;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedul(
            @RequestBody CreateScheduleRequest request
            ) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ){
        GetScheduleResponse result = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(){
        List<GetScheduleResponse> result = scheduleService.getAllSchedules();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
