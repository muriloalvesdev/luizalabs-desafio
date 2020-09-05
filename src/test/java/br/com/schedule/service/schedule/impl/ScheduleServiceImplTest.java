package br.com.schedule.service.schedule.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.repository.ScheduleRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.service.recipient.RecipientService;

class ScheduleServiceImplTest {

  private ScheduleRepository repository;
  private ScheduleServiceImpl service;
  private RecipientService recipientService;

  private ScheduleDataTransferObject dto;

  @BeforeEach
  void setUp() {
    this.dto = ScheduleDataTransferObject.newBuilder().build();
    this.repository = Mockito.spy(ScheduleRepository.class);
    this.recipientService = Mockito.spy(RecipientService.class);

    this.service = new ScheduleServiceImpl(this.repository, this.recipientService);
  }

  @Test
  void shouldSaveSchedule() {
    Schedule schedule = Schedule.newBuilder().build();
    Recipient recipient = Recipient.newBuilder().build();
    RecipientDataTransferObject recipientDataTransferObject =
        RecipientDataTransferObject.newBuilder().build();

    BDDMockito.given(this.repository.saveAndFlush(schedule)).willReturn(schedule);
    BDDMockito.given(this.recipientService.save(recipientDataTransferObject)).willReturn(recipient);

    service.save(dto);

    verify(this.recipientService, times(1)).save(any(RecipientDataTransferObject.class));
    verify(this.repository, times(1)).save(any(Schedule.class));
  }

}
