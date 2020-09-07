package br.com.schedule.convert.recipient.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationRecipient {

  private static final String REGEX_MATCHER_THIRTEEN_DIGITS_NUMBER = "\\d{2}-\\d{2}-\\d{9}";

  public static boolean isPhone(String recipient) {
    Pattern pattern = Pattern.compile(REGEX_MATCHER_THIRTEEN_DIGITS_NUMBER);
    Matcher matcher = pattern.matcher(recipient);
    return matcher.matches();
  }

  public static boolean isEmail(String recipient) {
    return EmailValidator.getInstance().isValid(recipient);
  }

}
