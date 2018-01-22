package tests;

import classes.AbstractMobileTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MyAccountPageMobile;

public class LoginMobileTest extends AbstractMobileTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginMobileTest.class.getName());

  @Test(testName = "Login por email exitoso", description = "ASQA-99 (1) Caso de prueba de login exitoso",
      groups = {"Probando"})
  public void succesfulLogin() throws Exception {
    startAppAndSelectCountry();
    MyAccountPageMobile myAccountPageMobile = loginUserByEmailMobileToMyAccount();
    Assert.assertTrue(myAccountPageMobile.isUserLogged(), "Usuario no se logueó con email");
    LOGGER.info("Usuario logueado con email correctamente");
  }

}
