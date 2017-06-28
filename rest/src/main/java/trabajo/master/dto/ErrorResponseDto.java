package trabajo.master.dto;

import java.util.List;

import lombok.Data;

/**
 * DTO que representa una respuesta de error.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Data
public class ErrorResponseDto {

  /** The status. */
  private int status;

  /** The message. */
  private String message;

  /** The description. */
  private String description;

  /** The errors. */
  protected List<ErrorDetails> errors;

  /**
   * Instantiates a new error response dto.
   */
  public ErrorResponseDto() {

  }

  /**
   * Instantiates a new error response dto.
   *
   * @param status
   *          the status
   * @param message
   *          the message
   * @param description
   *          the description
   * @param errors
   *          the errors
   */
  public ErrorResponseDto(int status, String message, String description,
      List<ErrorDetails> errors) {

    this.status = status;
    this.message = message;
    this.description = description;
    this.errors = errors;

  }
}
