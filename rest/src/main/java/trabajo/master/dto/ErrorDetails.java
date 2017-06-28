package trabajo.master.dto;

import lombok.Data;

/**
 * DTO que representa los detalles del error.
 *
 * @author Carmen Roberto Herrero
 * @version 1.0
 */
@Data
public class ErrorDetails {

  /** The type. */
  private String type;

  /** The description. */
  private String description;

  /**
   * Instantiates a new error details.
   */
  public ErrorDetails() {

  }

  /**
   * Instantiates a new error details.
   *
   * @param type
   *          the type
   * @param description
   *          the description
   */
  public ErrorDetails(String type, String description) {
    this.type = type;
    this.description = description;
  }
}
