package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ui.pages.components.SecondHeader;

public class HomePage extends WebComponent {
  private final SecondHeader secondHeader;

  public HomePage(WebDriver driver) {
    super(driver);
    secondHeader = new SecondHeader(driver);
  }

  @Step("Opening registration and login page from home page")
  public void goToRegAndLoginPage() {
    secondHeader.loginOrRegister();
  }
}
