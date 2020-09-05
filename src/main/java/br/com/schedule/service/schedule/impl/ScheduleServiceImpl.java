package br.com.schedule.service.schedule.impl;

import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.schedule.convert.ConvertSchedule;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.repository.ScheduleRepository;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.exception.ScheduleNotFoundException;
import br.com.schedule.service.recipient.impl.RecipientServiceImpl;
import br.com.schedule.service.schedule.ScheduleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class ScheduleServiceImpl implements ScheduleService {

  final ScheduleRepository repository;
  final RecipientServiceImpl recipientService;

  @Transactional
  @Override
  public Schedule save(ScheduleDataTransferObject dto) {
    Recipient recipient = recipientService.save(dto.getRecipient());
    Schedule schedule = ConvertSchedule.toEntity(dto, recipient);
    return repository.saveAndFlush(schedule);
  }

  @Override
  public void delete(String uuid) {
    Schedule schedule = repository.findByUuidAndStatus(UUID.fromString(uuid), Status.PENDING)
        .orElseThrow(() -> new ScheduleNotFoundException(uuid));
    schedule.setStatus(Status.DELETED);
    repository.save(schedule);
  }

  @Override
  public Page<ScheduleDataTransferObject> find(String status, Pageable pageable) {
    return repository.findByStatus(Status.find(status), pageable)
        .map(ConvertSchedule::toDataTransferObject);
  }
}
