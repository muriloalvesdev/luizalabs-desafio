package br.com.schedule.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;

public class ScheduleEntityProviderForTests implements ArgumentsProvider, ConstantsTests {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(Schedule.newBuilder().message(MESSAGE)
        .recipient(Recipient.newBuilder().recipient(RECIPIENT_EMAIL).build()).sendDate(SEND_DATE)
        .status(PENDING).type(EMAIL).build()).map(Arguments::of);
  }

}
