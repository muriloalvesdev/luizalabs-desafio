package br.com.schedule.providers;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.schedule.ConstantsTests;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.dto.ScheduleDataTransferObject;

public class ScheduleDTOProviderForTests implements ArgumentsProvider, ConstantsTests {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(ScheduleDataTransferObject.newBuilder().message(MESSAGE)
        .recipient(RecipientDataTransferObject.newBuilder().recipient(RECIPIENT).build())
        .sendDate(LocalDateTime.now().plusDays(7L)).status(PENDING.name()).type(EMAIL.name())
        .build()).map(Arguments::of);
  }

}
