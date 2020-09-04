package br.com.schedule.service.recipient.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import br.com.schedule.convert.ConvertRecipient;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.exception.RecipientNotFoundException;
import br.com.schedule.service.recipient.RecipientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class RecipientServiceImpl implements RecipientService {

  final RecipientRepository repository;

  @Transactional
  @Override
  public Recipient save(RecipientDataTransferObject dto) {
    return repository.save(ConvertRecipient.toEntity(dto));
  }

  @Override
  public List<RecipientDataTransferObject> find() {
    return repository.findAll().stream().map(ConvertRecipient::toDataTransferObject)
        .collect(Collectors.toList());
  }

  @Override
  public RecipientDataTransferObject find(String recipient) {
    return ConvertRecipient.toDataTransferObject(repository.findByRecipient(recipient)
        .orElseThrow(() -> new RecipientNotFoundException(recipient)));
  }

  @Transactional
  @Override
  public void delete(String recipient) {
    repository.delete(repository.findByRecipient(recipient)
        .orElseThrow(() -> new RecipientNotFoundException(recipient)));
  }

}
