package br.com.schedule.dto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ScheduleDataTransferObjectTest {

  @Test
  void shouldReturnException() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      ScheduleDataTransferObject.newBuilder().message(null).build();
    });

    assertTrue(exception instanceof NullPointerException);
    assertTrue(exception.getMessage().equals("message is marked non-null but is null"));
  }
}
