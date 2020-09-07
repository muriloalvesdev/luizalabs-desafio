package br.com.schedule.convert.recipient;

import org.springframework.util.StringUtils;
import br.com.schedule.convert.recipient.validation.ValidationRecipient;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConvertRecipient {

  public static Recipient toEntity(RecipientDataTransferObject recipient) {
    if (ValidationRecipient.isValid(recipient.getRecipient())
        && !StringUtils.containsWhitespace(recipient.getRecipient())) {
      return Recipient.newBuilder().recipient(recipient.getRecipient()).build();
    }
    throw new RecipientInvalidException(recipient.getRecipient());
  }

  public static RecipientDataTransferObject toDataTransferObject(Recipient recipient) {
    return RecipientDataTransferObject.newBuilder().recipient(recipient.getRecipient()).build();
  }
}
