package trabajo.master.exception;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import trabajo.master.dto.ErrorDetails;

/**
 * Excepcion que representa el error que se produce cuando no es válido un
 * determinado elemento de la entrada.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NotValidDataException extends Exception {

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
  public NotValidDataException(String message) {
    super(message);
    errors = Lists.newLinkedList();
  }

  /**
   * Instantiates a new not valid data exception.
   *
   * @param message the message
   * @param errors the errors
   */
  public NotValidDataException(String message, List<ErrorDetails> errors) {
    this(message);
    this.errors.addAll(errors);
  }
}
