package ui.pages.components;

import static org.openqa.selenium.support.PageFactory.initElements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.enitities.User;
import ui.pages.WebComponent;

@Getter
public class LoginForm extends WebComponent {
  @FindBy(id = "login-email")
  private WebElement emailInput;
  @FindBy(id = "login-password")
  private WebElement passwordInput;
  @FindBy(xpath = "//button[contains(text(), 'Войти') and @type = 'submit']")
  private WebElement submitRegBtn;
  @FindBy(css = ".logreg-block--login .logreg__header")
  private WebElement loginHeader;

  public LoginForm(WebDriver driver) {
    super(driver);
    initElements(driver, this);
  }

  public void setLogInfo(User user) {
    emailInput.sendKeys(user.getEmail());
    passwordInput.sendKeys(user.getPassword());
  }
}
