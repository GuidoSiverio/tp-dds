package frba.utn.edu.ar.tp_dds.exceptions;

public class InsufficientEspecialCharactersException extends InvalidPasswordException {

  public InsufficientEspecialCharactersException() {
    super("no poseer almenos un caracter especial.");
  }
}
