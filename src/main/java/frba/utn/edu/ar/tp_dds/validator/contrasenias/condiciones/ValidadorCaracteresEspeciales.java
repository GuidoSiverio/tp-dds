package frba.utn.edu.ar.tp_dds.validator.contrasenias.condiciones;

import frba.utn.edu.ar.tp_dds.exceptions.InsufficientEspecialCharactersException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador de contrasenas segun caracteres especiales.
 */
public class ValidadorCaracteresEspeciales implements Validable {

  /**
   * {@inheritDoc}
   */
  @Override
  public void validar(String password) {
    Pattern specialCharacterPattern =
        Pattern.compile("[!@#$%^&*()\\-_=+\\[\\]{};:\\\\|,.<>/?]");
    Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);
    if (!specialCharacterMatcher.find()) {
      throw new InsufficientEspecialCharactersException();
    }
  }
}
