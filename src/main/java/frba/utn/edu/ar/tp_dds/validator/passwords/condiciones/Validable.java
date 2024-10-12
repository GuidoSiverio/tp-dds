package frba.utn.edu.ar.tp_dds.validator.passwords.condiciones;

/**
 * Interfaz para las clases que validan contrasenas.
 */
public interface Validable {

  /**
   * Chequea si la contrasena es valida segun su criterio.
   *
   * @param password la contrasena.
   */
  void validar(String password);
}
