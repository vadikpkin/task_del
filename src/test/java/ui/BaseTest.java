package ui;

import static utils.PropertiesReader.getPropertyByName;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ui.enitities.User;
import ui.pages.AccountPage;
import ui.pages.HomePage;
import ui.pages.RegistrationAndLoginPage;

public class BaseTest {
  protected WebDriver driver;
  protected HomePage homePage;
  protected RegistrationAndLoginPage registrationAndLoginPage;
  protected AccountPage accountPage;
  protected User user;
  protected String url;

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    homePage = new HomePage(driver);
    registrationAndLoginPage = new RegistrationAndLoginPage(driver);
    accountPage = new AccountPage(driver);
    url = getPropertyByName("auchan_domain");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}
