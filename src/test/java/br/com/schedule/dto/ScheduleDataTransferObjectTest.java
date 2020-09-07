package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe responsável por validar atributo nulo do SchduleDTO")
public class ScheduleDataTransferObjectTest {

  @DisplayName("Validar atributo com a mesma anotação @NonNull.")
  @Test
  void shouldReturnException() {
    assertThrows(NullPointerException.class, () -> {
      ScheduleDataTransferObject.newBuilder().message(null).build();
    });
  }

}
