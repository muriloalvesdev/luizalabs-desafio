package br.com.schedule.convert;

import java.time.LocalDateTime;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.model.entity.Type;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;

public final class ConvertSchedule {
  private ConvertSchedule() {}

  public static Schedule toEntity(ScheduleDataTransferObject dto, Recipient recipient) {
    return Schedule.newBuilder().message(dto.getMessage()).recipient(recipient)
        .sendDate(LocalDateTime.parse(dto.getSendDate())).status(Status.PENDING)
        .type(Type.find(dto.getType())).build();
  }

  public static ScheduleDataTransferObject toDataTransferObject(Schedule schedule) {
    RecipientDataTransferObject recipientDataTransferObject =
        ConvertRecipient.toDataTransferObject(schedule.getRecipient());

    return ScheduleDataTransferObject.newBuilder().message(schedule.getMessage())
        .recipient(recipientDataTransferObject).sendDate(String.valueOf(schedule.getSendDate()))
        .status(schedule.getStatus().name()).type(schedule.getType().name()).build();
  }

}
