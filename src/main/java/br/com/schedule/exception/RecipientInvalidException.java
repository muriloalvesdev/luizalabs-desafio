package br.com.schedule.exception;

public class RecipientInvalidException extends RuntimeException {

  public RecipientInvalidException(String value) {
    super("Recipient [" + value + "] has space or/and is invalid!");
  }

}
