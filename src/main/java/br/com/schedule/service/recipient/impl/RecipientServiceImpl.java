package br.com.schedule.service.recipient.impl;

import org.springframework.stereotype.Service;
import br.com.schedule.convert.ConvertRecipient;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.service.recipient.RecipientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class RecipientServiceImpl implements RecipientService {

  final RecipientRepository repository;

  public Recipient save(RecipientDataTransferObject dto) {
    return repository.save(ConvertRecipient.toEntity(dto));
  }
}
