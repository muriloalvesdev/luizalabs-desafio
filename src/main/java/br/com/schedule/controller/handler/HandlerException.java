package br.com.schedule.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.schedule.domain.model.handler.ApiException;
import br.com.schedule.exception.InvalidArgumentException;
import br.com.schedule.exception.RecipientNotFoundException;
import br.com.schedule.exception.ScheduleNotFoundException;
import br.com.schedule.exception.TypeNotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
  private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

  @ExceptionHandler(ScheduleNotFoundException.class)
  public ResponseEntity<ApiException> handleScheduleNotFoundException(
      ScheduleNotFoundException ex) {
    return ResponseEntity.status(NOT_FOUND)
        .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
  }

  @ExceptionHandler(TypeNotFoundException.class)
  public ResponseEntity<ApiException> handleTypeNotFoundException(TypeNotFoundException ex) {
    return ResponseEntity.status(NOT_FOUND)
        .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
  }

  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<ApiException> handleInvalidArgumentException(InvalidArgumentException ex) {
    return ResponseEntity.status(BAD_REQUEST)
        .body(createResponse(ex.getMessage(), BAD_REQUEST.value()));
  }

  @ExceptionHandler(RecipientNotFoundException.class)
  public ResponseEntity<ApiException> handleRecipientNotFoundException(
      RecipientNotFoundException ex) {
    return ResponseEntity.status(NOT_FOUND)
        .body(createResponse(ex.getMessage(), NOT_FOUND.value()));
  }

  private ApiException createResponse(String message, int status) {
    return ApiException.newBuilder().message(message).status(status).build();
  }
}
