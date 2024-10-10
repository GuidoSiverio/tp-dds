package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientMayusLettersException extends InvalidPasswordException {

  public InsufficientMayusLettersException() {
    super("No poseer al menos una letra mayúscula.");
  }
}
