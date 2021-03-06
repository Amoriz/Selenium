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

public class VoirTrajets {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8090/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testVoirTrajets() throws Exception {
		selenium.open("/");
		selenium.click("link=Se connecter");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=j_username", "akraxx");
		selenium.type("id=j_password", "123456");
		selenium.click("css=input.btn.btn-default");
		selenium.waitForPageToLoad("30000");
		selenium.open("/profile/journeys");
		selenium.waitForPageToLoad("30000");
		assertEquals("Carpooling - Profile - Trajets", selenium.getTitle());
		assertEquals("36 Rue Paul Doumer, 59120 Loos, France", selenium.getText("//div[@id='journey-head-2']/div/div[2]"));
		selenium.open("/logout");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
