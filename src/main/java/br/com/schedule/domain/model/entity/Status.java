package br.com.schedule.domain.model.entity;

import java.util.Arrays;
import br.com.schedule.exception.StatusNotFoundException;

public enum Status {
  PENDING("PENDING"), SENT("SENT"), DELETED("DELETED");

  private String status;

  private Status(String status) {
    this.status = status;
  }

  private String getStatus() {
    return status;
  }

  public static Status find(String status) {
    return Arrays.asList(Status.values()).stream()
        .filter(s -> s.getStatus().equals(status.toUpperCase())).findFirst()
        .orElseThrow(() -> new StatusNotFoundException(status));
  }

}
