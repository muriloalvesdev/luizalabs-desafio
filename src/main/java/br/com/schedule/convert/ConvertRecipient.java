package br.com.schedule.convert;

import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;

public final class ConvertRecipient {
  private ConvertRecipient() {}

  public static Recipient toEntity(RecipientDataTransferObject recipient) {
    return Recipient.newBuilder().recipient(recipient.getRecipient()).build();
  }

  public static RecipientDataTransferObject toDataTransferObject(Recipient recipient) {
    return RecipientDataTransferObject.newBuilder().recipient(recipient.getRecipient()).build();
  }
}
