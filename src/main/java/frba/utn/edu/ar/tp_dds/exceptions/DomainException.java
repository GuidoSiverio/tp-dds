package frba.utn.edu.ar.tp_dds.exceptions;

import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {

  private final Integer statusCode;

  public DomainException(String message, Integer statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

}
