package ui.pages.components;

import static org.openqa.selenium.support.PageFactory.initElements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.WebComponent;

@Getter
public class SecondHeader extends WebComponent {
  @FindBy(className = "login_widget_not_logged_in")
  private WebElement loginOrRegisterBtn;
  @FindBy(className = "header__logged-name")
  private WebElement loggedInName;
  @FindBy(className = "header__logged-exit")
  private WebElement logOut;

  public SecondHeader(WebDriver driver) {
    super(driver);
    initElements(driver, this);
  }

  public void loginOrRegister() {
    loginOrRegisterBtn.click();
  }
}
