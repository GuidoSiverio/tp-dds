package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientMayusLettersException extends InvalidPasswordException {

  public InsufficientMayusLettersException() {
    super("no poseer al menos una letra mayúscula.");
  }
}
