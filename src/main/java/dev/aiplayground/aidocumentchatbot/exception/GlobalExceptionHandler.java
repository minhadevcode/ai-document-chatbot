package dev.aiplayground.aidocumentchatbot.exception;

import dev.aiplayground.aidocumentchatbot.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return new ErrorResponse("INVALID_INPUT", message);
    }
}
