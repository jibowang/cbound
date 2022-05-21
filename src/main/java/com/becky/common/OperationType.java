package com.becky.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

public enum OperationType {

  IN("in"),
  OUT("out")
  ;

  private String name;

  OperationType(String name) {
    this.name = name;
  }

  @JsonCreator
  public static OperationType from(String type) {
    if (StringUtils.isBlank(type)) {
      return null;
    }
    switch (type) {
      case "in":
        return IN;
      case "out":
        return OUT;
      default:
        throw new CBoundException("not supported operation: " + type);
    }
  }

  @JsonValue
  public String getType() {
    return this.name;
  }
}
