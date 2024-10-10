package frba.utn.edu.ar.tp_dds.validator.contrasenias.condiciones;

import frba.utn.edu.ar.tp_dds.exceptions.InsufficientMinusLettersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador de contrasenas segun letras minusculas.
 */
public class ValidadorDeLetrasMinusculas implements Validable {

  /**
   * {@inheritDoc}
   */
  @Override
  public void validar(String password) {
    Pattern lowerCasePattern = Pattern.compile("[a-z]");
    Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
    if (!lowerCaseMatcher.find()) {
      throw new InsufficientMinusLettersException();
    }
  }
}
