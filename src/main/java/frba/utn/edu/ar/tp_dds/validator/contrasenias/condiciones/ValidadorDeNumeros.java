package frba.utn.edu.ar.tp_dds.validator.contrasenias.condiciones;

import frba.utn.edu.ar.tp_dds.exceptions.InsufficientLengthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador de contrasenas segun los numeros que contiene.
 */
public class ValidadorDeNumeros implements Validable {

  /**
   * {@inheritDoc}
   */
  @Override
  public void validar(String password) {
    Pattern numberPattern = Pattern.compile("\\d");
    Matcher numberMatcher = numberPattern.matcher(password);
    if (!numberMatcher.find()) {
      throw new InsufficientLengthException();
    }
  }
}
