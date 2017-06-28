package trabajo.master.exception;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import trabajo.master.dto.ErrorDetails;

/**
 * Excepcion que representa el error que se produce cuando no es válido una
 * determinada coordenada de la ubicación.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NotValidCordinateException extends Exception {

  /** Serial version UID. */
  private static final long serialVersionUID = 5006813471828321098L;

  /** The errors. */
  protected final LinkedList<ErrorDetails> errors;

  /**
   * Constructor con parametros.
   *
   * @param message
   *          the message
   */
  public NotValidCordinateException(String message) {
    super(message);
    errors = Lists.newLinkedList();
  }
  
  /**
   * Instantiates a new not valid cordinate exception.
   *
   * @param message the message
   * @param errors the errors
   */
  public NotValidCordinateException(String message, List<ErrorDetails> errors) {
    this(message);
    this.errors.addAll(errors);
  }
}
