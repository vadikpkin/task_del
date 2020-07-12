package api.dto;


import lombok.Data;

@Data
public class ProjectGetDto {
  private long id;
  private String name;
  private int comment_count;
  private int color;
  private boolean shared;
  private int sync_id;
  private int order;
  private boolean favorite;
}
