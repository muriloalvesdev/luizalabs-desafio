package br.com.schedule.service.recipient.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.repository.RecipientRepository;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.providers.RecipientEntityProviderForTests;

@DisplayName("Classe de teste para o Serviço RecipientServiceImpl")
class RecipientServiceImplTest implements ConstantsTests {

  private RecipientRepository repository;
  private RecipientServiceImpl service;
  private RecipientDataTransferObject dto;

  @BeforeEach
  void setUp() {
    this.dto = RecipientDataTransferObject.newBuilder().recipient(RECIPIENT).build();
    this.repository = Mockito.spy(RecipientRepository.class);
    this.service = new RecipientServiceImpl(repository);
  }

  @ParameterizedTest(name = "{index}: ({0} + {1}))")
  @DisplayName("Deve testar o comportamento do metodo save() do Serviço.")
  @ArgumentsSource(RecipientEntityProviderForTests.class)
  void shouldSaveRecipient(Recipient recipient) {
    BDDMockito.given(this.repository.save(recipient)).willReturn(recipient);

    this.service.save(this.dto);

    verify(this.repository, times(1)).save(any(Recipient.class));
  }


  static class RecipientProviderForTests implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
      return Stream
          .of(RecipientDataTransferObject.newBuilder().recipient(ConstantsTests.RECIPIENT).build())
          .map(Arguments::of);
    }
  }
}
