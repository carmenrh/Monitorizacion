package trabajo.master.vo;

import lombok.Data;

/**
 * Instantiates a new respuesta indexado VO.
 */
@Data
public class RespuestaIndexadoVo {
  
  /** The index. */
  private String _index;
  
  /** The type. */
  private String _type;
  
  /** The id. */
  private String _id;
  
  /** The version. */
  private String _version;
  
  /** The result. */
  private String result;
  
  /** The shards. */
  private ShardVo _shards;
  
  /** The created. */
  private String created;
}
