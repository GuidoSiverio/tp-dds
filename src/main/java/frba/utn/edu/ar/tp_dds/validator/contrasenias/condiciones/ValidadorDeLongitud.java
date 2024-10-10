package frba.utn.edu.ar.tp_dds.validator.contrasenias.condiciones;


import frba.utn.edu.ar.tp_dds.exceptions.InsufficientLengthException;

/**
 * Validador de contrasenas segun longitud.
 */
public class ValidadorDeLongitud implements Validable {

  private static final int MIN_PASSWORD_LENGTH = 12;

  /**
   * {@inheritDoc}
   */
  @Override
  public void validar(String password) {
    if (!(password.length() >= MIN_PASSWORD_LENGTH)) {
      throw new InsufficientLengthException();
    }
  }
}
