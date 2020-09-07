package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe responsável por validar atributo nulo do SchduleDTO")
public class ScheduleDataTransferObjectTest {

  @DisplayName("Validar atributo com a mesma anotação @NonNull.")
  @Test
  void shouldReturnException() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      ScheduleDataTransferObject.newBuilder().message(null).build();
    });

    assertTrue(exception instanceof NullPointerException);
    assertTrue(exception.getMessage().equals("message is marked non-null but is null"));
  }
}
