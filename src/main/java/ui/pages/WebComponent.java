package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebComponent {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public WebComponent(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, 10);
  }
}
