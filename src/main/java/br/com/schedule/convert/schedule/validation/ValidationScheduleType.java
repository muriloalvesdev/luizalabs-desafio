package br.com.schedule.convert.schedule.validation;

import org.springframework.util.StringUtils;
import br.com.schedule.convert.recipient.validation.ValidationRecipient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationScheduleType {

  public static boolean isPhone(String recipient) {
    return ValidationRecipient.isPhone(recipient);
  }

  public static boolean isEmail(String recipient) {
    return ValidationRecipient.isEmail(recipient);
  }

  public static boolean isInvalid(String recipient) {
    return StringUtils.containsWhitespace(recipient);
  }
}
