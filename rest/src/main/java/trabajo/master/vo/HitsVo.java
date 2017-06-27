package trabajo.master.vo;

import java.util.List;

import lombok.Data;

/**
 * Instantiates a new hits vo.
 */
@Data
public class HitsVo {
  
  /** The total. */
  private Integer total;
  
  /** The max score. */
  private Integer max_score;
  
  /** The hits. */
  private List<SourceVo> hits;

}
