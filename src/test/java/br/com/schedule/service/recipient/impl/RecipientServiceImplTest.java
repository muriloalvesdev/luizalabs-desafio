package br.com.schedule.service.recipient.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.providers.RecipientEntityProviderForTests;
import br.com.schedule.service.recipient.RecipientService;

class RecipientServiceImplTest {

  private RecipientRepository repository;
  private RecipientService service;
  private RecipientDataTransferObject dto;

  @BeforeEach
  void setUp() {
    this.dto =
        RecipientDataTransferObject.newBuilder().recipient(ConstantsTests.RECIPIENT_EMAIL).build();
    this.repository = Mockito.spy(RecipientRepository.class);
    this.service = new RecipientServiceImpl(repository);
  }

  @ParameterizedTest
  @ArgumentsSource(RecipientEntityProviderForTests.class)
  void shouldSaveRecipient(Recipient recipient) {
    BDDMockito.given(this.repository.save(recipient)).willReturn(recipient);

    this.service.save(this.dto);
    verify(this.repository, times(1)).save(any(Recipient.class));
  }

  @ParameterizedTest
  @ArgumentsSource(RecipientEntityProviderForTests.class)
  void shouldReturnRecipientAlreadyExisting(Recipient recipient) {
    BDDMockito.given(this.repository.findByRecipient(recipient.getRecipient()))
        .willReturn(Optional.of(recipient));

    this.service.save(this.dto);

    verify(this.repository, times(1)).findByRecipient(recipient.getRecipient());
  }
}
