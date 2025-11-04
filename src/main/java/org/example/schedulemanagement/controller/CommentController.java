//package org.example.schedulemanagement.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.schedulemanagement.dto.comment.CreateCommentRequest;
//import org.example.schedulemanagement.dto.comment.CreateCommentResponse;
//import org.example.schedulemanagement.service.CommentService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentService commentService;
//
//    @PostMapping("/comments")
//    public ResponseEntity<CreateCommentResponse> createComment(
//            @RequestBody CreateCommentRequest request
//            ){
//        CreateCommentResponse result = commentService.createComment(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//}
