package br.com.schedule.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Recipient;

public class RecipientEntityProviderForTests implements ArgumentsProvider, ConstantsTests {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream.of(Recipient.newBuilder().recipient(RECIPIENT).build()).map(Arguments::of);
  }

}
