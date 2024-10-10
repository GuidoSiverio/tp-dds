package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientLengthException extends InvalidPasswordException {

  public InsufficientLengthException() {
    super("No posee una longitud mínima de 12 caracteres.");
  }
}
