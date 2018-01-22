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
 * Created by it on 13/07/17.
 */
public class MyAccountPageMobile extends MobilePage {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MyAccountPageMobile.class.getName());

  private static final String CONFIGURATION_AND_LEGALS = "CONFIGURATION_AND_LEGALS";
  private static final String LOGIN_OR_REGISTER = "LOGIN_OR_REGISTER";
  private static final String TRAVELKIT = "TRAVELKIT";
  private static final String PROFILE = "PROFILE";
  private static final String CLOSE_SESSION = "CLOSE_SESSION";
  private static final String FIND = "FIND";

  @AndroidFindBy(xpath = "//*[contains(@resource-id, 'profileName')]")
  @iOSFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeOther/XCUIElementTypeStaticText[1]")
  private WebElement profileName;

  public MyAccountPageMobile(AppiumDriver mobileDriver, String language) throws Exception {
    super(mobileDriver, language);
    initElements();
  }

  private void initElements() {
    translator.loadLanguages(CONFIGURATION_AND_LEGALS,
        translator.createWordList("Configuración y legales", "Configuração e informação legal"));
    translator.loadLanguages(LOGIN_OR_REGISTER,
        translator.createWordList("Inicia sesión o regístrate", "Iniciar sessão ou registre-se"));
    translator
        .loadLanguages(TRAVELKIT, translator.createWordList("Kit del Viajero", "Kit do viajante"));
    translator.loadLanguages(PROFILE, translator.createWordList("Perfil", "Perfil"));
    translator
        .loadLanguages(CLOSE_SESSION, translator.createWordList("Cerrar sesión", "Fechar sessão"));
    translator.loadLanguages(FIND, translator.createWordList("Buscar", "Buscar"));
  }


  public WebElement getTravelKitButton() throws Exception {
    waitForElementPresentLong(translator.trXpath(TRAVELKIT));
    return driver.findElement(By.xpath(translator.trXpath(TRAVELKIT)));
  }

  public WebElement getProfileButton() {
    return driver.findElement(By.xpath(translator.trXpath(PROFILE)));
  }

  public LoginPageMobile clickOnLoginOrRegister() throws Exception {
    waitForElementPresentShort(getLoginOrRegisterButton());
    tap(getLoginOrRegisterButton());
    return new LoginPageMobile(driver, language);
  }

  public WebElement getConfigurationAndLegalsButton() {
    return driver.findElement(By.xpath(translator.trXpath(CONFIGURATION_AND_LEGALS)));
  }

  public WebElement getLoginOrRegisterButton() {
    return driver.findElement(By.xpath("//*[@text='" + translator.tr(LOGIN_OR_REGISTER)
        + "' or @value='Inicie sessão ou registre-se' or @value='" + translator
        .tr(LOGIN_OR_REGISTER) + "']"));
  }

  public WebElement getCloseSessionButton() {
    return driver.findElement(By.xpath(translator.trXpath(CLOSE_SESSION)));
  }

  public boolean isUserLogged() throws Exception {
    try {
      changeImplicitlyWait(driver, 10000);
      waitForElementPresentLong(getCloseSessionButton());
      restoreImplicitlyWait(driver);
      return getCloseSessionButton().isDisplayed();
    } catch (Exception ex) {
      LOGGER.info("Usuario no se encuentra logueado");
      restoreImplicitlyWait(driver);
      return false;
    }
  }

  public HomePageMobile closeSession() throws Exception {
    waitForElementPresentShort(getCloseSessionButton());
    tap(getCloseSessionButton());
    return new HomePageMobile(driver, language);
  }

  public String getUserProfileName() {
    return this.profileName.getText();
  }

  public WebElement getFindButton() {
    String tr = translator.tr(FIND);
    return driver.findElement(By.xpath(
        "//*[@text = '" + tr + "' or @value = '" + tr + "' or @content-desc='" + tr
            + "' or @name = 'search']"));
  }

  public HomePageMobile clickOnFindButton() throws Exception {
    waitForElementPresentShort(getFindButton());
    tap(getFindButton());
    return new HomePageMobile(driver, language);
  }
}
