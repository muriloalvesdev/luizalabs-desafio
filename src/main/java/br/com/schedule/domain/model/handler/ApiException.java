package br.com.schedule.domain.model.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiException {
  private String message;
  private int status;
}
