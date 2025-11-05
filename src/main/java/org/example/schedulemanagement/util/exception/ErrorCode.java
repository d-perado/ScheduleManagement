package org.example.schedulemanagement.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //== 200 ==//
    SUCCESS(HttpStatus.OK, "OK"),

    //== 400 ==//
    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST,"지원하지 않는 Http Method 방식입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"유효하지 않은 패스워드 입니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 일정입니다."),
    COMMENT_OUT_OF_BOUND(HttpStatus.BAD_REQUEST, "작성가능한 댓글 개수가 초과 되었습니다.");

    private final HttpStatus status;
    private final String message;

    }