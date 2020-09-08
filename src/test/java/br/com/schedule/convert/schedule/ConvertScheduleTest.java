package br.com.schedule.convert.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Type;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;
import br.com.schedule.providers.ScheduleDTOProviderForTests;
import br.com.schedule.providers.ScheduleEntityProviderForTests;

@DisplayName("Classe responsável por testar a classe de Conversão da Entidade Schedule")
class ConvertScheduleTest implements ConstantsTests {

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve converter Schedule DTO para uma Entidade Schedule")
  void convertDataTransferObjectToEntity(ScheduleDataTransferObject dto) {
    Recipient recipient = Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build();
    Schedule schedule = ConvertSchedule.toEntity(dto, recipient);

    assertEquals(dto.getMessage(), schedule.getMessage());
    assertEquals(dto.getRecipient().getRecipient(), schedule.getRecipient().getRecipient());
    assertEquals(dto.getSendDate(), schedule.getSendDate());
    assertEquals(dto.getStatus(), schedule.getStatus().name());
    assertEquals(dto.getType(), schedule.getType().name());
  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleEntityProviderForTests.class)
  @DisplayName("Deve converter Entidade Schedule para um ScheduleDTO")
  void convertEntityToDataTransferObject(Schedule schedule) {
    ScheduleDataTransferObject dto = ConvertSchedule.toDataTransferObject(schedule);

    assertEquals(schedule.getMessage(), dto.getMessage());
    assertEquals(schedule.getRecipient().getRecipient(), dto.getRecipient().getRecipient());
    assertEquals(schedule.getSendDate(), dto.getSendDate());
    assertEquals(schedule.getType().name(), dto.getType());
    assertEquals(schedule.getStatus().name(), dto.getStatus());
  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve retornar exception, para um recipient que esteja inválido por conter espaçamento")
  void shouldReturnExceptionForRecipientInvalid(ScheduleDataTransferObject dto) {
    dto.getRecipient().setRecipient("a n y t h i n g");
    Exception exception = assertThrows(RecipientInvalidException.class, () -> {
      ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());
    });
    assertTrue(exception instanceof RecipientInvalidException);
    assertEquals("Recipient [a n y t h i n g] has space or/and is invalid!",
        exception.getMessage());

  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve setar o tipo da Entidade Schedule como EMAIL")
  void shouldSetTypeEmailInEntitySchedule(ScheduleDataTransferObject dto) {
    Schedule schedule =
        ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());
    assertEquals(Type.EMAIL, schedule.getType());
  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve setar o tipo da Entidade Schedule como SMS")
  void shouldSetTypeShortMessageServiceInEntitySchedule(ScheduleDataTransferObject dto) {
    dto.getRecipient().setRecipient(RECIPIENT_PHONE);
    dto.setType(Type.SMS.name());
    Schedule schedule =
        ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_PHONE).build());

    assertEquals(Type.SMS, schedule.getType());
  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve setar o tipo da Entidade Schedule como WHATSAPP")
  void shouldSetTypeWhatsappEntitySchedule(ScheduleDataTransferObject dto) {
    dto.getRecipient().setRecipient(RECIPIENT_PHONE);
    dto.setType(Type.WHATSAPP.name());
    Schedule schedule =
        ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_PHONE).build());

    assertEquals(Type.WHATSAPP, schedule.getType());
  }

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve setar o tipo da Entidade Schedule como EMAIL, mesmo quando o DTO informar um tipo diferente")
  void shouldSetTypeEmailInEntityScheduleEvenWhenDTOPassesDifferentType(
      ScheduleDataTransferObject dto) {
    dto.getRecipient().setRecipient(RECIPIENT_EMAIL);
    dto.setType(Type.WHATSAPP.name());
    Schedule schedule =
        ConvertSchedule.toEntity(dto, Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build());

    assertEquals(Type.EMAIL, schedule.getType());
  }
}
