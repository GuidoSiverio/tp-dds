package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientMinusLettersException extends InvalidPasswordException {

  public InsufficientMinusLettersException() {
    super("No poseer al menos una letra minúscula.");
  }
}
