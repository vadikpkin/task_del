package ui.enitities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
  private String userName;
  private String email;
  private String password;
}
