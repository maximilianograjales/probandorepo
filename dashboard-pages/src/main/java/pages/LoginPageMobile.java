package pages;

import classes.MobilePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by it on 11/07/17.
 */
public class LoginPageMobile extends MobilePage {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageMobile.class.getName());
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_local')]")
  @iOSFindBy(id = "com.despegar.login.LoginHomeViewController.despegarLoginButton")
  private WebElement loginWithEmailAccountButton;
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_facebook')]")
  @iOSFindBy(id = "com.despegar.login.LoginHomeViewController.facebookLoginButton")
  private WebElement loginWithFacebookButton;
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_google')]")
  @iOSFindBy(id = "com.despegar.login.LoginHomeViewController.googleLoginButton")
  private WebElement loginWithGoogleButton;
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_buynoaccount')]")
  @iOSFindBy(xpath = ".//*[@name='Compré y no tengo cuenta' or @name='Comprei e não tenho conta']")
  private WebElement buyNoAccountButton;
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_forgotpassword')]")
  @iOSFindBy(xpath = ".//*[@name='Olvidé mi contraseña' or @name='Esqueci minha senha']")
  private WebElement forgotPassword;
  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'button_fragmentlogin_register')]")
  @iOSFindBy(xpath = ".//*[@name='Quiero crear una cuenta' or @name='Quero criar uma conta']")
  private WebElement createAccountButton;
  @iOSFindBy(xpath = "//*[@name='DMCountrySelectionButton']")
  private WebElement continueCountryButton;

  public LoginPageMobile(AppiumDriver mobileDriver, String language) {
    super(mobileDriver, language);
  }

  public void selectCountry(String country) {
    if (country != null) {
      if (isElementPresent(driver, getCountryXpath(country))) {
        try {
          getCountryButton(country).click();
          if (elementExists(continueCountryButton)) {
            tap(continueCountryButton);
          }
        } catch (Exception ex) {
          LOGGER.error("There is an error clicking in the country " + country + " .Error : " + ex);
        }
      }
    }
  }

  public HomePageMobile closeLoginPopup() throws Exception {
    closePopUp();
    return new HomePageMobile(driver, language);
  }

  public LoginByEmailPageMobile selectLoginByEmail() throws Exception {
    tap(loginWithEmailAccountButton);
    return new LoginByEmailPageMobile(driver, language);
  }

  public void selectLoginByFacebook() throws Exception {
    tap(loginWithFacebookButton);
  }

  public WebElement getCountryButton(String country) {
    return driver.findElement(By.xpath(getCountryXpath(country)));
  }

  public String getCountryXpath(String country) {
    return "//*[@text = '" + country + "' or @name='" + country + "']";
  }

  public boolean isLoginPopupDisplayed(){
    return isElementPresentShortest(loginWithEmailAccountButton);
  }
}
