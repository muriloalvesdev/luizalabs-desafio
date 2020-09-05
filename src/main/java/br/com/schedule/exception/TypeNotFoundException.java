package br.com.schedule.exception;

public class TypeNotFoundException extends RuntimeException {

  public TypeNotFoundException(String fromString) {
    super("Type informed [" + fromString + "] found!");
  }
}
