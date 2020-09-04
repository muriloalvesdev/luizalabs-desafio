package br.com.schedule.domain.model.entity;

import java.util.Arrays;
import br.com.schedule.exception.TypeNotFoundException;

public enum Type {
  WHATSAPP("WHATSAPP"), SMS("SMS"), EMAIL("EMAIL"), PUSH("PUSH");

  private String propertie;

  private Type(String propertie) {
    this.propertie = propertie;
  }

  private String getPropertie() {
    return propertie;
  }

  public static Type find(String fromString) {
    return Arrays.asList(Type.values()).stream()
        .filter(type -> type.getPropertie().equals(fromString.toUpperCase().trim())).findFirst()
        .orElseThrow(() -> new TypeNotFoundException("Type informed [" + fromString + "] found!"));
  }
}
