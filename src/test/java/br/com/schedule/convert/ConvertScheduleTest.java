package br.com.schedule.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.model.entity.Type;
import br.com.schedule.dto.ScheduleDataTransferObject;

public class ConvertScheduleTest {

  private static final String RECIPIENT = "murilohenrique.ti@outlook.com.br";
  private static final String MESSAGE = "VOCÊ PASSOU EM NOSSO PROCESSO SELETIVO, BEM VINDO!";
  private static final LocalDateTime SEND_DATE = LocalDateTime.now().plusDays(7L);
  private static final Status PENDING = Status.PENDING;
  private static final Type EMAIL = Type.EMAIL;

  @Test
  void convertDataTransferObjectToEntity() {
    Recipient recipient = createRecipientEntity();

    ScheduleDataTransferObject dto = createScheduleDataTransferObject(recipient);
    Schedule schedule = ConvertSchedule.toEntity(dto, recipient);

    assertEquals(dto.getMessage(), schedule.getMessage());
    assertEquals(dto.getRecipient().getRecipient(), schedule.getRecipient().getRecipient());
    assertEquals(dto.getSendDate(), schedule.getSendDate());
    assertEquals(dto.getStatus(), schedule.getStatus().name());
    assertEquals(dto.getType(), schedule.getType().name());
  }

  @Test
  void convertEntityToDataTransferObject() {
    Recipient recipient = createRecipientEntity();

    Schedule schedule = Schedule.newBuilder().message(MESSAGE).recipient(recipient)
        .sendDate(SEND_DATE).status(PENDING).type(EMAIL).build();
    ScheduleDataTransferObject dto = ConvertSchedule.toDataTransferObject(schedule);

    assertEquals(schedule.getMessage(), dto.getMessage());
    assertEquals(schedule.getRecipient().getRecipient(), dto.getRecipient().getRecipient());
    assertEquals(schedule.getSendDate(), dto.getSendDate());
    assertEquals(schedule.getType().name(), dto.getType());
    assertEquals(schedule.getStatus().name(), dto.getStatus());
  }

  private ScheduleDataTransferObject createScheduleDataTransferObject(Recipient recipient) {
    return ScheduleDataTransferObject.newBuilder().message(MESSAGE)
        .recipient(ConvertRecipient.toDataTransferObject(recipient))
        .sendDate(LocalDateTime.now().plusDays(7L)).status(PENDING.name()).type(EMAIL.name())
        .build();
  }

  private Recipient createRecipientEntity() {
    return Recipient.newBuilder().recipient(RECIPIENT).build();
  }
}
