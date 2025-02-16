package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientLengthException extends InvalidPasswordException {

  public InsufficientLengthException() {
    super("no poseer una longitud mínima de 12 caracteres.");
  }
}
