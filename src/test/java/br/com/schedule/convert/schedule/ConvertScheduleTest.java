package br.com.schedule.convert.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.providers.ScheduleDTOProviderForTests;
import br.com.schedule.providers.ScheduleEntityProviderForTests;

@DisplayName("Classe responsável por testar a classe de Conversão da Entidade Schedule")
class ConvertScheduleTest implements ConstantsTests {

  @ParameterizedTest
  @ArgumentsSource(ScheduleDTOProviderForTests.class)
  @DisplayName("Deve converter Schedule DTO para uma Entidade Schedule")
  void convertDataTransferObjectToEntity(ScheduleDataTransferObject dto) {
    Recipient recipient = Recipient.newBuilder().recipient(RECIPIENT).build();
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
}
