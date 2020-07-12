package ui.data;

import static utils.PropertiesReader.getPropertyByName;

import com.github.javafaker.Faker;
import java.util.Locale;
import ui.enitities.User;

public final class UsersGenerator {

  public static User getRandomUnregValidUser() {
    Faker faker = new Faker(new Locale("en-US"));
    return new User()
        .setUserName(faker.name().firstName())
        .setEmail(faker.internet().emailAddress())
        .setPassword(faker.internet().password(8, 10) + "!@W");
  }

  public static User getRegisteredUser() {
    return new User()
        .setEmail(getPropertyByName("auchan_email"))
        .setUserName(getPropertyByName("auchan_username"))
        .setPassword(getPropertyByName("auchan_password"));

  }
}
