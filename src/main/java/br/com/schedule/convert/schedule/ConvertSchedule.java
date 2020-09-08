package br.com.schedule.convert.schedule;

import br.com.schedule.convert.recipient.ConvertRecipient;
import br.com.schedule.convert.schedule.validation.ValidationScheduleType;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Schedule.ScheduleBuilder;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.model.entity.Type;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.exception.RecipientInvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConvertSchedule {

  public static Schedule toEntity(ScheduleDataTransferObject dto, Recipient recipient) {

    if (ValidationScheduleType.isInvalid(dto.getRecipient().getRecipient())) {
      throw new RecipientInvalidException(dto.getRecipient().getRecipient());
    }

    ScheduleBuilder builder = Schedule.newBuilder().message(dto.getMessage()).recipient(recipient)
        .sendDate(dto.getSendDate()).status(Status.PENDING);

    if (ValidationScheduleType.isEmail(dto.getRecipient().getRecipient())) {
      return builder.type(Type.EMAIL).build();
    }

    if (ValidationScheduleType.isPhone(dto.getRecipient().getRecipient())) {
      builder.type(Type.find(dto.getType()));
    }
    return builder.build();
  }

  public static ScheduleDataTransferObject toDataTransferObject(Schedule schedule) {
    RecipientDataTransferObject recipientDataTransferObject =
        ConvertRecipient.toDataTransferObject(schedule.getRecipient());

    return ScheduleDataTransferObject.newBuilder().message(schedule.getMessage())
        .recipient(recipientDataTransferObject).sendDate(schedule.getSendDate())
        .status(schedule.getStatus().name()).type(schedule.getType().name()).build();
  }
}
