package br.com.schedule.convert.recipient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;
import br.com.schedule.providers.RecipientDTOProviderForTests;
import br.com.schedule.providers.RecipientEntityProviderForTests;

class ConvertRecipientTest {

  @ParameterizedTest
  @ArgumentsSource(RecipientDTOProviderForTests.class)
  void shouldConvertDataTransferObjectToEntity(RecipientDataTransferObject dto) {
    Recipient recipient = ConvertRecipient.toEntity(dto);
    assertEquals(dto.getRecipient(), recipient.getRecipient());
  }

  @ParameterizedTest
  @ArgumentsSource(RecipientEntityProviderForTests.class)
  void shouldConvertEntityToDataTransferObject(Recipient recipient) {
    RecipientDataTransferObject dto = ConvertRecipient.toDataTransferObject(recipient);
    assertEquals(recipient.getRecipient(), dto.getRecipient());
  }

  @Test
  void shouldReturnException() {
    RecipientDataTransferObject dtoInvalid =
        RecipientDataTransferObject.newBuilder().recipient("anything").build();
    Exception exception = assertThrows(RecipientInvalidException.class, () -> {
      ConvertRecipient.toEntity(dtoInvalid);
    });
    assertTrue(exception.getMessage().equals("Recipient [anything] has space or/and is invalid!"));
    assertTrue(exception instanceof RecipientInvalidException);
  }
}
