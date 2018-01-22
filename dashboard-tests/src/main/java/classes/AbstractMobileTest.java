package classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPageMobile;
import pages.MyAccountPageMobile;

public class AbstractMobileTest extends TestMobile {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMobileTest.class.getName());

  public static final String COUNTRY = "Argentina";

  /**
   * Metodo para loguear a usuario por mail desde un dispositivo movil.
   *
   * @return instancia de MyAccountPageMobile
   * @throws Exception si falla al iniciar sesion
   * @author maximiliano.grajales
   */
  protected MyAccountPageMobile loginUserByEmailMobileToMyAccount()
      throws Exception {
    return MobileEntryPoint
        .loginMobileAndroidByEmail(getDriver());
  }

  /**
   * Metodo que abre la app y selecciona un pais de la lista.
   *
   * @return HomePageMobile devuelve una pagina del tipo HomePageMobile
   * @throws Exception falla si no puede cerrar el popup o elegir el pais.
   * @author maximiliano.grajales
   */
  public LoginPageMobile startAppAndSelectCountry()
      throws Exception {
    startDriver();
    return new LoginPageMobile(getDriver(), language);
  }
}
