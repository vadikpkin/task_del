package api.data;

import api.dto.TaskCreateDto;
import com.github.javafaker.Faker;
import java.util.Locale;

public class TaskGenerator {

  public static TaskCreateDto getRandomTask() {
    Faker faker = new Faker(new Locale("en-US"));
    return new TaskCreateDto()
        .setContent(faker.funnyName().name())
        .setDue_lang("en")
        .setDue_string("tomorrow at 12:00")
        .setPriority(faker.number().numberBetween(1, 4));
  }
}
