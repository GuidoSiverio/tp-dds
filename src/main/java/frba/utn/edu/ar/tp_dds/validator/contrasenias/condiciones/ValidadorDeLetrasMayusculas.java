package frba.utn.edu.ar.tp_dds.validator.contrasenias.condiciones;

import frba.utn.edu.ar.tp_dds.exceptions.InsufficientMayusLettersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador de contrasenas segun letras mayusculas.
 */
public class ValidadorDeLetrasMayusculas implements Validable {

  /**
   * {@inheritDoc}
   */
  @Override
  public void validar(String password) {
    Pattern upperCasePattern = Pattern.compile("[A-Z]");
    Matcher upperCaseMatcher = upperCasePattern.matcher(password);
    if (!upperCaseMatcher.find()) {
      throw new InsufficientMayusLettersException();
    }
  }
}
