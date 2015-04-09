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

public class Inscription {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8090/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testInscription() throws Exception {
		selenium.open("/");
		selenium.click("link=Se connecter");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=j_username", "bob");
		selenium.type("id=j_password", "123456");
		selenium.click("//input[@value=\"S'enregistrer\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Erreur lors de l'enregistrement : Votre pseudo doit au moins contenir 5 caractères", selenium.getText("//body/div/div[2]/ul/li"));
		selenium.type("id=j_username", "bobby");
		selenium.type("id=j_password", "123");
		selenium.click("//input[@value=\"S'enregistrer\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Erreur lors de l'enregistrement : Votre mot de passe doit au moins contenir 5 caractères", selenium.getText("//body/div/div[2]/ul/li"));
		
		selenium.type("id=j_username", "amaury");
		selenium.type("id=j_password", "123456");
		selenium.click("//input[@value=\"S'enregistrer\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Erreur lors de l'enregistrement : Un utilisateur avec le pseudo amaury existe déja", selenium.getText("//body/div/div[2]/ul/li"));
		
		selenium.type("id=j_username", "bobby");
		selenium.type("id=j_password", "123456");
		selenium.click("//input[@value=\"S'enregistrer\"]");
		selenium.waitForPageToLoad("30000");
		assertEquals("Vous pouvez maintenant vous connecter avec le compte bobby", selenium.getText("//body/div/div[2]/ul/li"));
		selenium.click("css=input.btn.btn-default");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isElementPresent("link=bobby"));
		selenium.open("/logout");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
