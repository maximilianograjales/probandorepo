package pages;

import classes.Constant;
import classes.MobilePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by it on 13/07/17.
 */
public class HomePageMobile extends MobilePage {

  private static final String MY_ACCOUNT = "MY_ACCOUNT";

  private static final String MY_TRIPS = "MY_TRIPS";

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'android:id/button1')]")
  @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Não, obrigado' or @name='No, gracias']")
  private WebElement popUpExperienceOK;

  @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
  private WebElement popUpGeoLocationOK;

  @AndroidFindBy(className = "android.support.v7.widget.RecyclerView")
  @iOSFindBy(xpath = "//*[@name='TitleHomeModule']")
  private WebElement offersElement;

  public HomePageMobile(AppiumDriver mobileDriver, String language) throws Exception {
    super(mobileDriver, language);
    initElements();
    closeLocationsAlerts();
  }


  private void initElements() {
    translator.loadLanguages(MY_ACCOUNT, translator.createWordList("Mi cuenta", "Minha conta"));
    translator.loadLanguages(MY_TRIPS, translator.createWordList("Mis viajes", "Minhas viagens"));
  }


  /**
   * Va a la pagina de Mi cuenta cerrando los alerts de geolocation si aparecen.
   *
   * @return MyAccountPageAndroid
   * @throws Exception si falla al hacer Tap en mi cuenta
   * @author Carlos Torres
   */
  public MyAccountPageMobile clickOnMyAccount() throws Exception {
    closeLocationsAlerts();
    waitForElementPresentLong(offersElement);
    Thread.sleep(Constant.TIMEOUT_MIL_5000);
    //Por si venimos de gmail
    switchToNativeContext();
    WebElement button = getMyAccountButton();
    waitForElementPresentLong(button);
    tap(button);

    return new MyAccountPageMobile(driver, language);
  }

  public WebElement getMyAccountButton() {
    return driver.findElement(By.xpath(translator.trXpath(MY_ACCOUNT)));
  }

  public WebElement getMyTripsButton() {
    return driver.findElement(By.xpath(translator.trXpath(MY_TRIPS)));
  }

  /**
   * Cierra los alerts de Mejorar la Experiencia por Localización y el que acepta el uso del GPS.
   *
   * @throws Exception En caso de no cerrar los Alerts
   * @author Carlos Torres
   */
  public void closeLocationsAlerts() throws Exception {
    if (isElementPresentShortest(popUpExperienceOK)) {
      tap(popUpExperienceOK);
      if (isElementPresentShortest(popUpGeoLocationOK)) {
        waitForElementPresentShort(popUpGeoLocationOK);
        tap(popUpGeoLocationOK);
        openAndCloseNotifications();
      }
    }
  }

  public MyAccountPageMobile simpleClickOnMyAccount() throws Exception {
    waitForElementPresentLong(getMyAccountButton());
    Thread.sleep(Constant.TIMEOUT_MIL_3000);
    tap(getMyAccountButton());
    return new MyAccountPageMobile(driver, language);
  }

}
