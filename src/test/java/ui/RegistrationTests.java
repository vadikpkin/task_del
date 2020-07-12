package ui;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ui.data.UsersGenerator.getRandomUnregValidUser;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Registration")
public class RegistrationTests extends BaseTest {

  @BeforeMethod()
  public void openSite() {
    driver.get(url);
    driver.manage().window().maximize();
    homePage.goToRegAndLoginPage();
  }

  @AfterMethod()
  public void logOutIfLogged() {
    accountPage.logOut();
  }

  @Test(description = "Registration of user with valid user info")
  @Severity(CRITICAL)
  void validReg() {
    user = getRandomUnregValidUser();
    registrationAndLoginPage.register(user);
    String loggedInName = accountPage.getUserName();
    String loggedInEmail = accountPage.getEmail();
    assertEquals(loggedInName, user.getUserName(), "Wrong name of logged in user");
    assertEquals(loggedInEmail, user.getEmail(), "Wrong email of logged in user");
  }

  @Test(description = "Registration user with nonvalid password")
  @Severity(CRITICAL)
  void invalidPassReg() {
    user = getRandomUnregValidUser().setPassword("passwordwithoutnumber");
    registrationAndLoginPage.register(user);
    boolean isPasswordErrorVisible = registrationAndLoginPage.isPasswordErrorMessageVisibleReg();
    String passwordBorderColor = registrationAndLoginPage.getBorderColorOfPasswordInputReg();
    String message = registrationAndLoginPage.getPasswordErrorMessageReg();
    assertEquals(passwordBorderColor, "rgb(228, 32, 46)", "Wrong color of border on password field");
    assertTrue(isPasswordErrorVisible, "Error of invalid password is not visible");
    assertEquals(message, "Пароль должен содержать как минимум 1 цифру, 1 символ в " +
            "верхнем регистре, 1 символ в нижнем регистре, 1 специальный символ (!$*-.=?@)",
        "Wrong error message for invalid password");
  }

  @Test(description = "Registration with special chars username")
  @Severity(CRITICAL)
  void specialCharsUsernameReg() {
    user = getRandomUnregValidUser().setUserName("Vasya!!@@##");
    registrationAndLoginPage.register(user);
    boolean isErrorPopVisible = registrationAndLoginPage.isErrorPopErrorVisibleReg();
    String message = registrationAndLoginPage.getErrorPopMessageReg();
    assertTrue(isErrorPopVisible, "Error pop up is not visible");
    assertEquals(message, "Ошибка: Имя не должно содержать специальных символов");
  }

  @Test(description = "Registarion with empty username")
  @Severity(CRITICAL)
  void emptyUsernameReg() {
    user = getRandomUnregValidUser().setUserName(EMPTY);
    registrationAndLoginPage.register(user);
    String passwordBorderColor = registrationAndLoginPage.getBorderColorOfUsernameInputReg();
    assertEquals(passwordBorderColor, "rgb(228, 32, 46)", "Wrong color of username border");
  }

  @Test(description = "Registration with empty password")
  @Severity(CRITICAL)
  void emptyPasswordReg() {
    user = getRandomUnregValidUser().setPassword(EMPTY);
    registrationAndLoginPage.register(user);
    boolean isPasswordErrorVisible = registrationAndLoginPage.isPasswordErrorMessageVisibleReg();
    String passwordBorderColor = registrationAndLoginPage.getBorderColorOfPasswordInputReg();
    String actualMessage = registrationAndLoginPage.getPasswordErrorMessageReg();
    assertEquals(passwordBorderColor, "rgb(228, 32, 46)", "Username checked failed");
    assertTrue(isPasswordErrorVisible, "Error of invalid password is not visible");
    assertEquals(actualMessage, "Пароль должен содержать не менее 8 символов",
        "Wrong error message for invalid password");
  }

  @Test(description = "Registration with empty email")
  @Severity(CRITICAL)
  void emptyEmailReg() {
    user = getRandomUnregValidUser().setEmail(EMPTY);
    registrationAndLoginPage.register(user);
    String passwordBorderColor = registrationAndLoginPage.getBorderColorOfEmailInputReg();
    assertEquals(passwordBorderColor, "rgb(228, 32, 46)", "Wrong color of email border");
  }
}
