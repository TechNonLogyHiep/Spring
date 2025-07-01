package org.example.baitap_apispringboot.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    CATEGORY_NOT_FOUND(1001,"Không tìm thấy category",HttpStatus.BAD_REQUEST),
    COURSE_NOT_FOUND(1002,"Không tìm thấy course",HttpStatus.BAD_REQUEST),
    INVALID_NUM_HOURS(1004, "Số giờ phải >= 0",HttpStatus.BAD_REQUEST),
    INVALID_NUM_SESSIONS(1005, "Số buổi phải >= 0",HttpStatus.BAD_REQUEST),
    INVALID_NAME_LESSON(1006,"Tên bài học là duy nhất không được trống dài từ 3 - 200 kí tự",HttpStatus.BAD_REQUEST),
    INVALID_NAME_COURSE(1006,"Tên khóa học là duy nhất, không được trống, dài từ 2 - 100 kí tự",HttpStatus.BAD_REQUEST),

    LESSONS_NOT_FOUND(1003,"Không tìm thấy lesson",HttpStatus.BAD_REQUEST);

    final int statusCode;
    final String message;
    final HttpStatus httpStatus;

    ErrorCode(int statusCode, String message, HttpStatus httpStatus) {
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
