package trabajo.master.vo;

import lombok.Data;

/**
 * The Class RespuestaBusquedaVo.
 */

/**
 * Instantiates a new respuesta busqueda vo.
 */
@Data
public class RespuestaBusquedaVo {
  
  /** The index. */
  private String _index;
  
  /** The type. */
  private String _type;
  
  /** The id. */
  private String _id;
  
  /** The version. */
  private Integer _version;
  
  /** The found. */
  private Boolean found;
  
  /** The source. */
  private ModeloVo _source;
  
}
