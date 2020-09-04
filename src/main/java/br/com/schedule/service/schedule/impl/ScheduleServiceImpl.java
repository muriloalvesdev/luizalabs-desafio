package br.com.schedule.service.schedule.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import br.com.schedule.convert.ConvertSchedule;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.repository.ScheduleRepository;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.exception.ScheduleNotFoundException;
import br.com.schedule.service.recipient.RecipientService;
import br.com.schedule.service.schedule.ScheduleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Service
public class ScheduleServiceImpl implements ScheduleService {

  final ScheduleRepository repository;
  final RecipientService recipientService;

  @Transactional
  @Override
  public UUID save(ScheduleDataTransferObject dto) {
    Recipient recipient = recipientService.save(dto.getRecipient());
    Schedule schedule = ConvertSchedule.toEntity(dto, recipient);
    return repository.saveAndFlush(schedule).getUuid();
  }

  @Override
  public ScheduleDataTransferObject find(String uuid) {
    return repository.findById(UUID.fromString(uuid)).map(ConvertSchedule::toDataTransferObject)
        .orElseThrow(() -> new ScheduleNotFoundException(uuid));
  }

  @Override
  public List<ScheduleDataTransferObject> find() {
    return repository.findAll().stream().map(ConvertSchedule::toDataTransferObject)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(String uuid) {
    Schedule schedule = repository.findById(UUID.fromString(uuid))
        .orElseThrow(() -> new ScheduleNotFoundException(uuid));
    schedule.setStatus(Status.CANCELLED);
    repository.save(schedule);
  }


}
