package br.com.schedule.convert.recipient.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe responsável por testar a clase de Validação do Recipient")
class ValidationRecipientTest {

  private static final String EMAIL_VALID = "murilohenrique.ti@outlook.com.br";
  private static final String PHONE_VALID = "55-16-994496217";

  private static final String EMAIL_NOT_VALID = "murilohenrique.tioutlook.com.br";
  private static final String PHONE_NOT_VALID = "55 16 99449 6217";


  @DisplayName("Deve enviar o email e o telefone para validação e retornar 'true', pois ambos são válidos")
  @Test
  void shouldValidate() {
    boolean emailValidated = ValidationRecipient.isEmail(EMAIL_VALID);
    boolean phoneValidated = ValidationRecipient.isPhone(PHONE_VALID);

    assertTrue(emailValidated);
    assertTrue(phoneValidated);
  }

  @DisplayName("Deve enviar o email e o telefone para validação e retornar 'false', pois ambos estão inválidos")
  @Test
  void shouldReturnInvalid() {
    boolean emailValidated = ValidationRecipient.isEmail(EMAIL_NOT_VALID);
    boolean phoneValidated = ValidationRecipient.isPhone(PHONE_NOT_VALID);

    assertFalse(emailValidated);
    assertFalse(phoneValidated);
  }

}
