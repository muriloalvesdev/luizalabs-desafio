package br.com.schedule.exception;

public class InvalidArgumentException extends RuntimeException {

  public InvalidArgumentException(String value) {
    super("Should not contains white space in [" + value + "]");
  }

}
