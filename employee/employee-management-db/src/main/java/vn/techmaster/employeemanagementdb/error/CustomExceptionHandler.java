package vn.techmaster.employeemanagementdb.error;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vn.techmaster.employeemanagementdb.model.MessageResponse;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex, WebRequest request) {
        ApiError notFoundError = new ApiError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(notFoundError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<MessageResponse> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("file is too large!"));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MessageResponse> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new MessageResponse("Could not upload this file"));
    }

    // xử lý tất cả exception chưa được khai báo
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleAllException(Exception ex, WebRequest request) {
        ApiError error = new ApiError(new Date(), "Server Error", request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
