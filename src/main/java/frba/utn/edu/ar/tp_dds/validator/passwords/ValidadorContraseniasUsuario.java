package frba.utn.edu.ar.tp_dds.validator.passwords;

import frba.utn.edu.ar.tp_dds.validator.passwords.condiciones.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Componente para validar las contrasenas de usuario segun las validaciones requeridas.
 */
@Component
public class ValidadorContraseniasUsuario {

  /**
   * Lista de validaciones asignadas al validador.
   **/
  private static final List<Validable> validaciones = Arrays.asList(
          new ValidadorDeLongitud(), new ValidadorCaracteresEspeciales(),
          new ValidadorDeLetrasMayusculas(), new ValidadorDeLetrasMinusculas(),
          new ValidadorDeNumeros(), new ValidadorWorstPasswords());

  public ValidadorContraseniasUsuario() {
  }

  public void validarContrasenia(String password) {
    validaciones.forEach(t -> t.validar(password));
  }

}
