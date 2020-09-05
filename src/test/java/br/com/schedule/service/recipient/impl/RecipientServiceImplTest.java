package br.com.schedule.service.recipient.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;

class RecipientServiceImplTest {

  private RecipientRepository repository;
  private RecipientServiceImpl service;
  private Recipient recipient;
  private RecipientDataTransferObject dto;

  @BeforeEach
  void setUp() {
    this.dto = RecipientDataTransferObject.newBuilder().recipient("testando").build();
    this.repository = Mockito.spy(RecipientRepository.class);
    this.recipient = Recipient.newBuilder().recipient("testando").build();
    this.service = new RecipientServiceImpl(repository);
  }

  @Test
  void shouldSaveRecipient() {
    BDDMockito.given(this.repository.save(this.recipient)).willReturn(this.recipient);

    this.service.save(this.dto);

    verify(this.repository, times(1)).save(any(Recipient.class));
  }

}
