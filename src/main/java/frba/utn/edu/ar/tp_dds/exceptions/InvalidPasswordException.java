package frba.utn.edu.ar.tp_dds.exceptions;

/**
 * Excepcion lanzada ante la introduccion de una contrasena invalida.
 */
public abstract class InvalidPasswordException extends DomainException {

  public InvalidPasswordException(String error) {
    super("La contraseña ingresada es invalida por : " + error, 400);
  }

}
