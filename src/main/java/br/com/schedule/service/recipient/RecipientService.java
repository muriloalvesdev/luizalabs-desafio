package br.com.schedule.service.recipient;

import java.util.List;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;

public interface RecipientService {
  Recipient save(RecipientDataTransferObject dto);

  void delete(String recipient);

  RecipientDataTransferObject find(String recipient);

  List<RecipientDataTransferObject> find();
}
