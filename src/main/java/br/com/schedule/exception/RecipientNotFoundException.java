package br.com.schedule.exception;

public class RecipientNotFoundException extends RuntimeException {

  public RecipientNotFoundException(String recipient) {
    super("Recipient not found with [" + recipient + "]");
  }

}
