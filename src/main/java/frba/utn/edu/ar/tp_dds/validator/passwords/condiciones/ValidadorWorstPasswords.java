package frba.utn.edu.ar.tp_dds.validator.passwords.condiciones;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

import frba.utn.edu.ar.tp_dds.exceptions.WorstPasswordContainsYourPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validador de contrasenas segun las peores contrasenas de la OWASP.
 */
public class ValidadorWorstPasswords implements Validable {
  Logger LOGGER = LoggerFactory.getLogger(ValidadorWorstPasswords.class);
  /**
   * Comprueba si una contrasena de usuario cumple con los estandares OWASP. Chequea contra una
   * lista de las 10.000 peores contrasenas segun OWASP.
   *
   * @param password la contrasena a analizar
   */
  @Override
  public void validar(String password) {
    HashSet<String> worstPasswords = getWorstPasswords();
    if (worstPasswords.contains(password)) {
      throw new WorstPasswordContainsYourPasswordException();
    }
  }

  /**
   * Obtiene las 10.000 peores contrasenas de la OWASP desde un archivo de texto y las devuelve como
   * HashSet.
   *
   * @return un HashSet con las 10.000 peores contrasenas.
   */
  private HashSet<String> getWorstPasswords() {
    HashSet<String> worstPasswords = new HashSet<>();

    try {
      LOGGER.info("Cargando peores contraseñas de la OWASP...");
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(
              new FileInputStream("src/main/resources/worst_passwords/worst-passwords.txt"),
              StandardCharsets.UTF_8));
      String password;
      while ((password = reader.readLine()) != null) {
        worstPasswords.add(password);
      }
      reader.close();
    } catch (IOException e) {
      throw new RuntimeException("Error al leer el archivo de contraseñas: " + e.getMessage());
    }
    return worstPasswords;
  }
}
