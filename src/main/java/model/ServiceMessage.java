package model;

import java.util.Objects;

public class ServiceMessage {

  private Integer code;
  private String type;
  private String message;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceMessage response = (ServiceMessage) o;
    return Objects.equals(code, response.code) && Objects
        .equals(type, response.type) && Objects.equals(message, response.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, type, message);
  }

  @Override
  public String toString() {
    return "Response{" +
        "code=" + code +
        ", type='" + type + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}