package frba.utn.edu.ar.tp_dds.exceptions;

public class WorstPasswordContainsYourPasswordException extends InvalidPasswordException {

  public WorstPasswordContainsYourPasswordException() {
    super("Encontrarse dentro de la lista de peores contraseñas.");
  }
}
