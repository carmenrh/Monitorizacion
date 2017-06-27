package trabajo.master.dto;

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

  /**
   * Constructor por defecto.
   */
  public ErrorResponseDto() {

  }

  /**
   * Constructor con parametros.
   *
   * @param status the status
   * @param message the message
   * @param description the description
   */
  public ErrorResponseDto(int status, String message, String description) {

    this.status = status;
    this.message = message;
    this.description = description;

  }
}
