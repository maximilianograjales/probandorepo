package classes;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePageMobile;
import pages.LoginByEmailPageMobile;
import pages.LoginPageMobile;
import pages.MyAccountPageMobile;

/**
 * Created by it on 17/07/17.
 */
public class MobileEntryPoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(MobileEntryPoint.class.getName());

  /**
   * Atajo para loguearse en Android con email y password de produccion.
   *
   * @return MyAccountPageMobile
   * @throws Exception si falla el login
   * @author maximiliano.grajales
   */
  public static MyAccountPageMobile loginMobileAndroidByEmail(AppiumDriver driver)
      throws Exception {
    LoginPageMobile loginPageMobile = new LoginPageMobile(driver, "br");
    LoginByEmailPageMobile loginByEmailPageMobile = loginPageMobile.selectLoginByEmail();
    loginByEmailPageMobile
        .loginUser("aftersale.hopper@gmail.com", "Aftersale123");
    LOGGER.info("Usuario logueado correctamente!");
    HomePageMobile homePageMobile = new HomePageMobile(driver, "br");
    return homePageMobile.clickOnMyAccount();
  }

}
