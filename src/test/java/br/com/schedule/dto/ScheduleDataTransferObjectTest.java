package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ScheduleDataTransferObjectTest {

  /**
   * Validar somente um atributo, existem outros com a mesma anotação @NonNull, mas acredito que
   * validando apenas um atributo seria o sufiente para ESTE CASO.
   */

  @Test
  void shouldReturnException() {
    assertThrows(NullPointerException.class, () -> {
      ScheduleDataTransferObject.newBuilder().message(null).build();
    });
  }

}
