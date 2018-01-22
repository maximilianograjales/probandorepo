package pages;

import classes.MobilePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

/**
 * Created by it on 13/07/17.
 */
public class LoginByEmailPageMobile extends MobilePage {

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlocalloginstep1_accept')]")
  @iOSFindBy(xpath = ".//XCUIElementTypeScrollView/../XCUIElementTypeButton")
  private WebElement acceptUsernameButton;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlocalloginstep2_password')]")
  @iOSFindBy(xpath = "//*[@name='Minha senha' or @name='Mi contraseña']")
  private WebElement myPasswordButton;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlocalloginstep3_enter')]")
  @iOSFindBy(xpath = ".//XCUIElementTypeScrollView/../XCUIElementTypeButton")
  private WebElement loginButton;

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlocalloginstep2_magiclink')]")
  @iOSFindBy(xpath = "//*[@name='Ingreso rápido' or @name='acesso rápido']")
  private WebElement quickAccessButton;


  public LoginByEmailPageMobile(AppiumDriver mobileDriver, String language) {
    super(mobileDriver, language);
  }

  public void clickOnMyPasswordButton() {
    myPasswordButton.click();
  }

  public void clickOnLoginButton() throws Exception {
    tap(loginButton);
  }

  public void clickOnAcceptUsername() throws Exception {
    tap(acceptUsernameButton);
  }

  public HomePageMobile loginUserToHomePage(String email, String password) throws Exception {
    loginUser(email, password);
    return new HomePageMobile(driver, language);
  }

  public void loginUser(String email, String password) throws Exception {
    enterEmailAccount(email);
    clickOnAcceptUsername();
    clickOnMyPasswordButton();
    enterPassword(password);
    clickOnLoginButton();
  }

  public void clickOnQuickAccessButton() throws Exception {
    tap(quickAccessButton);
  }

  public void loginUserQuickAccess(String email, String password) throws Exception {
    enterEmailAccount(email);
    clickOnAcceptUsername();
    clickOnQuickAccessButton();
  }

}
