package br.com.schedule.convert.recipient.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe responsável por testar a clase de Validação do Recipient")
class ValidationRecipientTest {

  private static final String EMAIL_VALUE = "murilohenrique.ti@outlook.com.br";
  private static final String PHONE_VALUE = "55-16-994496217";


  @Test
  void shouldValidate() {
    boolean emailValidated = ValidationRecipient.isValid(EMAIL_VALUE);
    boolean phoneValidated = ValidationRecipient.isValid(PHONE_VALUE);
    
    assertTrue(emailValidated);
    assertTrue(phoneValidated);
  }

}
