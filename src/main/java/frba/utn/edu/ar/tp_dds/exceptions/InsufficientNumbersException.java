package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientNumbersException extends InvalidPasswordException {

  public InsufficientNumbersException() {
    super("No posee al menos un número.");
  }
}
