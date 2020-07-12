package ui.pages;

import static org.openqa.selenium.support.PageFactory.initElements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.enitities.User;
import ui.pages.components.LoginForm;
import ui.pages.components.RegForm;
import ui.pages.components.SecondHeader;

public class RegistrationAndLoginPage extends WebComponent {
  private final SecondHeader secondHeader;
  private final RegForm regForm;
  private final LoginForm loginForm;
  @FindBy(xpath = "//section[@class='logreg']//div[text()='Регистрация']")
  private WebElement registration;
  @FindBy(xpath = "//section[@class='logreg']//div[text()='Войти']")
  private WebElement login;
  @FindBy(id = "errormessage-cnt-pop")
  private WebElement errorPopUp;

  public RegistrationAndLoginPage(WebDriver driver) {
    super(driver);
    secondHeader = new SecondHeader(driver);
    regForm = new RegForm(driver);
    loginForm = new LoginForm(driver);
    initElements(driver, this);
  }

  @Step("Registrating user on register form")
  public void register(User user) {
    registration.click();
    wait.until(ExpectedConditions.visibilityOf(regForm.getFormHeader()));
    regForm.setRegInfo(user);
    regForm.getSubmitRegBtn().click();
  }

  @Step("Loggin into site on login form")
  public void login(User user) {
    login.click();
    wait.until(ExpectedConditions.visibilityOf(loginForm.getLoginHeader()));
    loginForm.setLogInfo(user);
    loginForm.getSubmitRegBtn().click();
  }

  public String getPasswordErrorMessageReg() {
    return wait.until(ExpectedConditions
        .visibilityOf(regForm.getPasswordErrorMessage())).getText();
  }

  public boolean isPasswordErrorMessageVisibleReg() {
    return wait.until(ExpectedConditions
        .visibilityOf(regForm.getPasswordErrorMessage())).isDisplayed();
  }

  public String getBorderColorOfUsernameInputReg() {
    return getBorderColor(regForm.getFirstNameInput());
  }

  public String getBorderColorOfPasswordInputReg() {
    return getBorderColor(regForm.getPasswordInput());
  }

  public String getBorderColorOfPasswordInputLog() {
    return getBorderColor(loginForm.getPasswordInput());
  }

  public String getBorderColorOfEmailInputReg() {
    return getBorderColor(regForm.getEmailInput());
  }

  public String getBorderColorOfEmailInputLog() {
    return getBorderColor(loginForm.getEmailInput());
  }

  public boolean isErrorPopErrorVisibleReg() {
    return wait.until(ExpectedConditions.visibilityOf(errorPopUp)).isDisplayed();
  }

  public String getErrorPopMessageReg() {
    return wait.until(ExpectedConditions.visibilityOf(errorPopUp)).getText();
  }

  private String getBorderColor(WebElement webElement) {
    return webElement.getCssValue("border-color");
  }
}
