package br.com.schedule.convert.recipient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;

class ConvertRecipientTest implements ConstantsTests {

  @Test
  void shouldConvertDataTransferObjectToEntity() {
    RecipientDataTransferObject dto = createRecipientDataTransferObject();
    Recipient recipient = ConvertRecipient.toEntity(dto);

    assertEquals(dto.getRecipient(), recipient.getRecipient());
  }

  @Test
  void shouldConvertEntityToDataTransferObject() {
    Recipient recipient = Recipient.newBuilder().recipient(RECIPIENT).build();
    RecipientDataTransferObject dto = ConvertRecipient.toDataTransferObject(recipient);

    assertEquals(recipient.getRecipient(), dto.getRecipient());
  }

  @Test
  void shouldReturnException() {
    RecipientDataTransferObject dto =
        RecipientDataTransferObject.newBuilder().recipient("anything").build();
    assertThrows(RecipientInvalidException.class, () -> {
      ConvertRecipient.toEntity(dto);
    });
  }

  private RecipientDataTransferObject createRecipientDataTransferObject() {
    return RecipientDataTransferObject.newBuilder().recipient(RECIPIENT).build();
  }

}
