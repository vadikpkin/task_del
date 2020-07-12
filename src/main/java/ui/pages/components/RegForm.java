package ui.pages.components;

import static org.openqa.selenium.support.PageFactory.initElements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.enitities.User;
import ui.pages.WebComponent;

@Getter
public class RegForm extends WebComponent {
  @FindBy(id = "firstname")
  private WebElement firstNameInput;
  @FindBy(id = "email")
  private WebElement emailInput;
  @FindBy(id = "password")
  private WebElement passwordInput;
  @FindBy(id = "confirm_password")
  private WebElement confirmPasswordInput;
  @FindBy(id = "undosubscribe")
  private WebElement subscribeCheckbox;
  @FindBy(xpath = "//button[contains(text(), 'Зарегистрироваться') and @type = 'submit']")
  private WebElement submitRegBtn;
  @FindBy(css = ".logreg-block--reg .logreg__header")
  private WebElement formHeader;
  @FindBy(className = "logreg__span-info")
  private WebElement passwordErrorMessage;

  public RegForm(WebDriver driver) {
    super(driver);
    initElements(driver, this);
  }

  public void setRegInfo(User user) {
    firstNameInput.sendKeys(user.getUserName());
    emailInput.sendKeys(user.getEmail());
    passwordInput.sendKeys(user.getPassword());
    confirmPasswordInput.sendKeys(user.getPassword());
    if (!subscribeCheckbox.isSelected()) {
      subscribeCheckbox.click();
    }
  }
}
