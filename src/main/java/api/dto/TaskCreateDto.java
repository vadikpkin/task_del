package api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskCreateDto {
  private String content;
  private String due_string;
  private String due_lang;
  private int priority;
}