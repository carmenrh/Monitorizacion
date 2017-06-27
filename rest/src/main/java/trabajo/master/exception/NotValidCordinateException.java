package trabajo.master.exception;

/**
 * Excepcion que representa el error que se produce cuando no es válido una
 * determinada coordenada de la ubicación.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
public class NotValidCordinateException extends Exception {

  /** Serial version UID. */
  private static final long serialVersionUID = 5006813471828321098L;

  /**
   * Constructor con parametros.
   *
   * @param message
   *          the message
   */
  public NotValidCordinateException(String message) {
    super(message);
  }
}
