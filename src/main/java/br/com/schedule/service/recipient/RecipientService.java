package br.com.schedule.service.recipient;

import br.com.schedule.dto.RecipientDataTransferObject;

import java.util.Optional;

public interface RecipientService<T> {
  Optional<T> save(RecipientDataTransferObject dto);
}
