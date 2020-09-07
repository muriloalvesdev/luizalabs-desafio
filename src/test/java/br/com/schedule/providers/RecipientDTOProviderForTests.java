package br.com.schedule.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import br.com.schedule.ConstantsTests;
import br.com.schedule.dto.RecipientDataTransferObject;

public class RecipientDTOProviderForTests implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
    return Stream
        .of(RecipientDataTransferObject.newBuilder().recipient(ConstantsTests.RECIPIENT_EMAIL).build())
        .map(Arguments::of);
  }

}
