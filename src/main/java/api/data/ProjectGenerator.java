package api.data;

import api.dto.ProjectCreateDto;
import com.github.javafaker.Faker;
import java.util.Locale;

public class ProjectGenerator {

  public static ProjectCreateDto getRandomProjectCreateDto() {
    Faker faker = new Faker(new Locale("en-US"));
    return new ProjectCreateDto().setName(faker.rockBand().name());
  }
}
