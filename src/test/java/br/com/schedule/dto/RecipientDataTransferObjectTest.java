package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class RecipientDataTransferObjectTest {

  @Test
  void shouldReturnException() {
    assertThrows(NullPointerException.class, () -> {
      RecipientDataTransferObject.newBuilder().recipient(null).build();
    });
  }
}
