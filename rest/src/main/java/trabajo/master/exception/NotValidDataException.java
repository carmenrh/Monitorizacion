package trabajo.master.exception;

/**
 * Excepcion que representa el error que se produce cuando no es válido un
 * determinado elemento de la entrada.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
public class NotValidDataException extends Exception {

  /** Serial version UID. */
  private static final long serialVersionUID = 5006813471828321098L;

  /**
   * Constructor con parametros.
   *
   * @param message
   *          the message
   */
  public NotValidDataException(String message) {
    super(message);
  }
}
