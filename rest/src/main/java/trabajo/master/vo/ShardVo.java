package trabajo.master.vo;

import lombok.Data;

/**
 * Instantiates a new shard vo.
 */
@Data
public class ShardVo {
  
  /** The total. */
  private String total;
  
  /** The successful. */
  private String successful;
  
  /** The failed. */
  private String failed;
}

