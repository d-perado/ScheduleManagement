package org.example.schedulemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.comment.CreateCommentRequest;
import org.example.schedulemanagement.dto.comment.CreateCommentResponse;
import org.example.schedulemanagement.dto.schedule.*;
import org.example.schedulemanagement.service.CommentService;
import org.example.schedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController             //@Controller 어노테이션과 차이는 RequestBody 를 포함한 ResponseEntity 를 반환한다는 점에 있음.
@RequiredArgsConstructor //초기화 되지않은 final field, @nonnull 등 필수값을 필요로하는 필드에대해 생성자를 자동으로 생성해줌.
public class ScheduleController {//
    private final ScheduleService scheduleService; //ScheduleService 를 사용하기위해 선언
    private final CommentService commentService;   //CommentService 를 사용하기위해 선언

    @PostMapping("/schedules") //Post메소드를 사용하기위한 어노테이션
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @Valid //Request에 있는 필수값을 예외처리하기 위해서 선언
            @RequestBody
            CreateScheduleRequest request
    ) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{scheduleId}") //Get메소드를 사용하기위해서 선언 REST 에서는 GET 메서드에 RequestBody를 포함하지 않을것을 권장.
    public ResponseEntity<GetScheduleWithCommentResponse> getSchedule(
            @PathVariable Long scheduleId //URL형식으로 사용자로부터 scheduleId 값을 받기위해 선언
    ) {
        GetScheduleWithCommentResponse result = scheduleService.getScheduleWithComment(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules") //GET 메서드 사용하기위해 선언
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(
            @RequestParam String writer //URL파라미터를 통해 writer 값을 받기위해 선언
    ) {
        List<GetScheduleResponse> result = scheduleService.getAllSchedulesByWriter(writer);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{scheduleId}") //PUT 메서드를 사용하기위해 선언
    // PATCH와의 차이는 리소스 전체를 이용해 수정할 것인지 일부만 수정할 것인지 차이.
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequest request
    ) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{scheduleId}") //DELETE 메서드를 사용하기 위해 선언
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.deleteSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request
    ) {
        CreateCommentResponse result = commentService.createComment(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
