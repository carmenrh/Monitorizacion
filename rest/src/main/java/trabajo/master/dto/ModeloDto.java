package trabajo.master.dto;

import lombok.Data;

/**
 * Instantiates a new modelo DTO.
 * 
 * @author Carmen Roberto Herrero
 * @version 1.0
 * 
 */
@Data
public class ModeloDto {

  // /** The id. */
  // private Integer id;

  /** The tipo. */
  private String tipo;

  /** The fabricante. */
  private String fabricante;

  /** The modelo. */
  private String modelo;

  /** The valorActual. */
  private String valorActual;

  /** The localidad. */
  private String localidad;

  /** The ubicacion. */
  private GeoPointDto ubicacion;

  /** The fechaMedida. */
  private String fechaMedida;

  /** The zona. */
  private String zona;

  /** The intervaloMedida. */
  private String intervaloMedida;
}
