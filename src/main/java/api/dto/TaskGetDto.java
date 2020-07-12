package api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskGetDto {
  private long id;
  private long project_id;
  private long section_id;
  private int order;
  private String content;
  private boolean completed;
  private int[] label_ids;
  private int priority;
  private int comment_count;
  private String created;
  private Due due;
  private String url;
}
