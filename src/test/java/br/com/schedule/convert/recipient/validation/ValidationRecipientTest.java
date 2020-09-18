package br.com.schedule.convert.recipient.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import br.com.schedule.ConstantsTests;

class ValidationRecipientTest {

  @Test
  void shouldValidate() {
    boolean emailValidated = ValidationRecipient.isEmail(ConstantsTests.RECIPIENT_EMAIL);
    boolean phoneValidated = ValidationRecipient.isPhone(ConstantsTests.RECIPIENT_PHONE);

    assertTrue(emailValidated);
    assertTrue(phoneValidated);
  }

  @Test
  void shouldReturnInvalid() {
    boolean emailValidated = ValidationRecipient.isEmail(ConstantsTests.RECIPIENT_EMAIL_NOT_VALID);
    boolean phoneValidated = ValidationRecipient.isPhone(ConstantsTests.RECIPIENT_PHONE_NOT_VALID);

    assertFalse(emailValidated);
    assertFalse(phoneValidated);
  }

}
