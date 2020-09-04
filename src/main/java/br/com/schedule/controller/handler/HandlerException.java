package br.com.schedule.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.schedule.domain.model.handler.ApiException;
import br.com.schedule.exception.RecipientNotFoundException;
import br.com.schedule.exception.ScheduleNotFoundException;
import br.com.schedule.exception.TypeNotFoundException;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ScheduleNotFoundException.class)
  public ResponseEntity<ApiException> handleScheduleNotFoundException(
      ScheduleNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(TypeNotFoundException.class)
  public ResponseEntity<ApiException> handleTypeNotFoundException(TypeNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  @ExceptionHandler(RecipientNotFoundException.class)
  public ResponseEntity<ApiException> handleRecipientNotFoundException(
      RecipientNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(createResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
  }

  private ApiException createResponse(String message, int status) {
    return ApiException.newBuilder().message(message).status(status).build();
  }
}
