package br.com.schedule.convert.recipient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;
import br.com.schedule.providers.RecipientDTOProviderForTests;
import br.com.schedule.providers.RecipientEntityProviderForTests;

class ConvertRecipientTest implements ConstantsTests {

  @ParameterizedTest
  @DisplayName("Deve converter DTO em uma Entidade")
  @ArgumentsSource(RecipientDTOProviderForTests.class)
  void shouldConvertDataTransferObjectToEntity(RecipientDataTransferObject dto) {
    Recipient recipient = ConvertRecipient.toEntity(dto);
    assertEquals(dto.getRecipient(), recipient.getRecipient());
  }

  @ParameterizedTest
  @DisplayName("Deve converter uma entidade em um DTO")
  @ArgumentsSource(RecipientEntityProviderForTests.class)
  void shouldConvertEntityToDataTransferObject(Recipient recipient) {
    RecipientDataTransferObject dto = ConvertRecipient.toDataTransferObject(recipient);
    assertEquals(recipient.getRecipient(), dto.getRecipient());
  }

  @DisplayName("Deve retornar exception informando que o Recipient é inválido por não ter um formato do tipo email ou telefone celular")
  @Test
  void shouldReturnException() {
    RecipientDataTransferObject dto =
        RecipientDataTransferObject.newBuilder().recipient("anything").build();
    assertThrows(RecipientInvalidException.class, () -> {
      ConvertRecipient.toEntity(dto);
    });
  }
}
