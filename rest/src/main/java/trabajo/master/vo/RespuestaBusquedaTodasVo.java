package trabajo.master.vo;

import lombok.Data;

/**
 * Instantiates a new respuesta indexado VO.
 */

/**
 * Instantiates a new respuesta busqueda todas vo.
 */
@Data
public class RespuestaBusquedaTodasVo {

  /** The took. */
  private Integer took;

  /** The timed out. */
  private Boolean timed_out;

  /** The shards. */
  private ShardVo _shards;

  /** The hits. */
  private HitsVo hits;

}
