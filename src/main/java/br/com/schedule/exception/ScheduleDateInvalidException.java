package br.com.schedule.exception;

import java.time.LocalDateTime;

public class ScheduleDateInvalidException extends RuntimeException {
  public ScheduleDateInvalidException(LocalDateTime sendDate) {
    super("send_date [" + sendDate + "] is invalid");
  }

}
