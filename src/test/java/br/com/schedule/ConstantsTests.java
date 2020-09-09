package br.com.schedule;

import java.time.LocalDateTime;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.model.entity.Type;

public interface ConstantsTests {
  static final String RECIPIENT_EMAIL = "murilohenrique.ti@outlook.com.br";
  static final String RECIPIENT_PHONE = "55-16-994496217";

  static final LocalDateTime SEND_DATE = LocalDateTime.now().plusDays(7L);
  static final String MESSAGE = "VOCÊ PASSOU EM NOSSO PROCESSO SELETIVO, BEM VINDO!";
  static final Status PENDING = Status.PENDING;
  static final Type EMAIL = Type.EMAIL;

  static final String PROFILE_TEST = "test";
}
