package ui.pages;

import static org.openqa.selenium.support.PageFactory.initElements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.components.SecondHeader;

public class AccountPage extends WebComponent {
  private final SecondHeader header;
  @FindBy(xpath = "//td[text()='Имя']/following-sibling::td")
  private WebElement userName;
  @FindBy(xpath = "//td[text()='Электронная почтa']/following-sibling::td")
  private WebElement email;

  public AccountPage(WebDriver driver) {
    super(driver);
    header = new SecondHeader(driver);
    initElements(driver, this);
  }

  public String getUserName() {
    return wait.until(ExpectedConditions.visibilityOf(userName)).getText();
  }

  public String getEmail() {
    return wait.until(ExpectedConditions.visibilityOf(email)).getText();
  }

  @Step("Logging out on account page")
  public void logOut() {
    if (!header.getLoginOrRegisterBtn().isDisplayed()) {
      header.getLogOut().click();
      wait.until(ExpectedConditions
          .visibilityOf(header.getLoginOrRegisterBtn()));
    }
  }
}
