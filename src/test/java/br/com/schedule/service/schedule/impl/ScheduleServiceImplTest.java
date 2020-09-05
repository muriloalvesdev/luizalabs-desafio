package br.com.schedule.service.schedule.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.repository.ScheduleRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.service.recipient.RecipientService;

class ScheduleServiceImplTest implements ConstantsTests {

  private ScheduleRepository repository;

  private ScheduleServiceImpl service;
  private RecipientService recipientService;

  private ScheduleDataTransferObject scheduleDataTransferObject;
  private RecipientDataTransferObject recipientDataTransferObject;

  Schedule schedule;

  @BeforeEach
  void setUp() {
    schedule = Schedule.newBuilder().build();
    recipientDataTransferObject =
        RecipientDataTransferObject.newBuilder().recipient(RECIPIENT).build();

    this.scheduleDataTransferObject = ScheduleDataTransferObject.newBuilder().message(MESSAGE)
        .sendDate(SEND_DATE).sendDate(LocalDateTime.now().plusDays(7L)).status(PENDING.name())
        .type(EMAIL.name()).recipient(recipientDataTransferObject).build();

    this.repository = Mockito.spy(ScheduleRepository.class);
    this.recipientService = Mockito.spy(RecipientService.class);

    this.service = new ScheduleServiceImpl(this.repository, this.recipientService);
  }

  @Test
  void shouldSaveSchedule() {
    Recipient recipient = Recipient.newBuilder().build();

    BDDMockito.given(this.repository.saveAndFlush(any(Schedule.class))).willReturn(schedule);
    BDDMockito.given(this.recipientService.save(recipientDataTransferObject)).willReturn(recipient);

    service.save(scheduleDataTransferObject);

    verify(this.recipientService, times(1)).save(any(RecipientDataTransferObject.class));
    verify(this.repository, times(1)).saveAndFlush(any(Schedule.class));
  }

  @Test
  void shouldDeleteLogicallySchedule() {
    UUID uuid = UUID.randomUUID();
    BDDMockito.given(this.repository.findByUuidAndStatus(uuid, PENDING))
        .willReturn(Optional.of(this.schedule));
    BDDMockito.given(this.repository.save(any(Schedule.class))).willReturn(this.schedule);

    this.service.delete(uuid.toString());

    verify(repository, times(1)).findByUuidAndStatus(uuid, PENDING);
    verify(repository, times(1)).save(any(Schedule.class));
  }

}
