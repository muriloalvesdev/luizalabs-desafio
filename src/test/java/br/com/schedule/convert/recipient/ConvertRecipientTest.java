package br.com.schedule.convert.recipient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import br.com.schedule.ConstantsTests;
import br.com.schedule.convert.recipient.ConvertRecipient;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;

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

  private RecipientDataTransferObject createRecipientDataTransferObject() {
    return RecipientDataTransferObject.newBuilder().recipient(RECIPIENT).build();
  }

}
