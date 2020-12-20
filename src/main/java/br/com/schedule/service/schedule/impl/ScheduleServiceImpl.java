package br.com.schedule.service.schedule.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import br.com.schedule.convert.recipient.ConvertRecipient;
import br.com.schedule.convert.schedule.ConvertSchedule;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.repository.ScheduleRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.exception.ScheduleDateInvalidException;
import br.com.schedule.exception.ScheduleNotFoundException;
import br.com.schedule.service.recipient.RecipientService;
import br.com.schedule.service.schedule.ScheduleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
class ScheduleServiceImpl implements ScheduleService<ScheduleDataTransferObject> {

  final ScheduleRepository repository;
  final RecipientService<RecipientDataTransferObject> recipientService;

  @Transactional
  @Override
  public Optional<UUID> save(ScheduleDataTransferObject dto) {
    if (LocalDateTime.now().isAfter(dto.getSendDate())) {
      throw new ScheduleDateInvalidException(dto.getSendDate());
    }
    return recipientService.save(dto.getRecipient())
            .map(recipient -> ConvertSchedule.toEntity(dto, ConvertRecipient.toEntity(recipient)))
            .map(schedule -> repository.saveAndFlush(schedule).getUuid());
  }

  @Override
  public Optional<ScheduleDataTransferObject> delete(String uuid) {
    Schedule schedule = repository.findByUuidAndStatus(UUID.fromString(uuid), Status.PENDING)
        .orElseThrow(() -> new ScheduleNotFoundException(uuid));
    schedule.setStatus(Status.DELETED);
    return Optional.ofNullable(ConvertSchedule.toDataTransferObject(repository.save(schedule)));
  }

  @Override
  public Optional<ScheduleDataTransferObject> find(String uuid) {
    return repository.findById(UUID.fromString(uuid)).map(ConvertSchedule::toDataTransferObject);
  }
}
