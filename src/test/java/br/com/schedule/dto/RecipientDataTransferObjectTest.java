package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe responsável por testar se um atributo do DTO realmente não pode ser nulo")
public class RecipientDataTransferObjectTest {

  @Test
  @DisplayName("Deve retornar NullPointerException")
  void shouldReturnException() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      RecipientDataTransferObject.newBuilder().recipient(null).build();
    });

    assertTrue(exception instanceof NullPointerException);
    assertTrue(exception.getMessage().equals("recipient is marked non-null but is null"));
  }
}
