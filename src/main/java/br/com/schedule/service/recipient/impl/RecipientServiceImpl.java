package br.com.schedule.service.recipient.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.schedule.convert.recipient.ConvertRecipient;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.service.recipient.RecipientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class RecipientServiceImpl implements RecipientService<RecipientDataTransferObject> {

  final RecipientRepository repository;

  public Optional<RecipientDataTransferObject> save(RecipientDataTransferObject dto) {
    return repository
        .findByRecipient(dto.getRecipient())
        .map(entity -> Optional.ofNullable(ConvertRecipient.toDataTransferObject(entity)))
        .orElse(
            Optional.ofNullable(
                ConvertRecipient.toDataTransferObject(
                    repository.save(ConvertRecipient.toEntity(dto)))));
  }
}
