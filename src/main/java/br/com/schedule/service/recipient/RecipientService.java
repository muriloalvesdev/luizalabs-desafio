package br.com.schedule.service.recipient;

import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;

public interface RecipientService {
  Recipient save(RecipientDataTransferObject dto);
}
