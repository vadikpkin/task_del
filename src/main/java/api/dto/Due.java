package api.dto;

import lombok.Data;

@Data
public class Due {
  private boolean recurring;
  private String date;
  private String datetime;
  private String string;
  private String timezone;
}
