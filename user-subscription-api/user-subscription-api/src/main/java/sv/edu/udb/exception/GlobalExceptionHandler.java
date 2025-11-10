package sv.edu.udb.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@ControllerAdvice
class GlobalExceptionHandler {

    private ApiError build(HttpStatus status, String message, String field){
        ApiError e = new ApiError();
        e.setStatus(status.value());
        e.setError(status.getReasonPhrase());
        e.setMessage(message);
        e.setField(field);
        e.setTimestamp(OffsetDateTime.now());
        return e;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> badRequest(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(build(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getField()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> unreadable(HttpMessageNotReadableException ex) {
        String msg = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : "Invalid request body";
        return ResponseEntity.badRequest().body(build(HttpStatus.BAD_REQUEST, msg, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        FieldError fe = ex.getBindingResult().getFieldError();
        String field = fe != null ? fe.getField() : null;
        String msg   = fe != null ? fe.getDefaultMessage() : "Validation error";
        return ResponseEntity.badRequest().body(build(HttpStatus.BAD_REQUEST, msg, field));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(build(HttpStatus.NOT_FOUND, ex.getMessage(), null));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> conflict(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(build(HttpStatus.CONFLICT, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> generic(Exception ex, HttpServletRequest req) throws Exception {
        String uri = req.getRequestURI();
        if (uri.startsWith("/v3/") || uri.startsWith("/swagger-ui") || uri.startsWith("/h2-console")) {
            throw ex;
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", null));
    }
}

