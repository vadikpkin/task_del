package ui;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ui.data.Colors.RED_RGB;
import static ui.data.UsersGenerator.getRandomUnregValidUser;
import static ui.data.UsersGenerator.getRegisteredUser;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTests extends BaseTest {

  @BeforeMethod()
  public void openSite() {
    driver.get(url);
    driver.manage().window().maximize();
    homePage.goToRegAndLoginPage();
  }

  @AfterMethod()
  public void logOut() {
    accountPage.logOut();
  }

  @Test(description = "Login with registered user")
  @Severity(CRITICAL)
  void validLogin() {
    user = getRegisteredUser();
    registrationAndLoginPage.login(user);
    String loggedInName = accountPage.getUserName();
    String loggedInEmail = accountPage.getEmail();
    assertEquals(loggedInName, user.getUserName(), "Wrong name of logged in user");
    assertEquals(loggedInEmail, user.getEmail(), "Wrong email of logged in user");
  }

  @Test(description = "Login with unregistered user")
  @Severity(CRITICAL)
  void unRegisteredUserLogin() {
    user = getRandomUnregValidUser();
    registrationAndLoginPage.login(user);
    boolean isErrorPopVisible = registrationAndLoginPage.isErrorPopErrorVisibleReg();
    String message = registrationAndLoginPage.getErrorPopMessageReg();
    assertTrue(isErrorPopVisible, "Error pop up is not visible");
    assertEquals(message, "Неверный логин или пароль. Проверьте, пожалуйста.");
  }

  @Test(description = "Login empty email")
  @Severity(CRITICAL)
  void emptyEmailLogin() {
    user = getRandomUnregValidUser().setEmail(EMPTY);
    registrationAndLoginPage.login(user);
    String emailBorderColor = registrationAndLoginPage.getBorderColorOfEmailInputLog();
    assertEquals(emailBorderColor, RED_RGB, "Wrong color of username border");
  }

  @Test(description = "Login empty password")
  @Severity(CRITICAL)
  void emptyPasswordLogin() {
    user = getRandomUnregValidUser().setPassword(EMPTY);
    registrationAndLoginPage.login(user);
    String passwordBorderColor = registrationAndLoginPage.getBorderColorOfPasswordInputLog();
    assertEquals(passwordBorderColor, RED_RGB, "Wrong color of username border");
  }
}
