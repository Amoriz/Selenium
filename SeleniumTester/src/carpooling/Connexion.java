package carpooling;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class Connexion {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8090/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testConnexion() throws Exception {
		selenium.open("/");
		selenium.click("link=Se connecter");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=j_username", "test");
		selenium.type("id=j_password", "test");
		selenium.click("css=button.btn.btn-default");
		selenium.waitForPageToLoad("30000");
		assertEquals("Invalid username and password!", selenium.getText("css=div.alert.alert-danger"));
		selenium.type("id=j_username", "akraxx");
		selenium.type("id=j_password", "123456");
		selenium.click("css=button.btn.btn-default");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("link=akraxx"));
		selenium.open("/logout");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("link=Se connecter"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
