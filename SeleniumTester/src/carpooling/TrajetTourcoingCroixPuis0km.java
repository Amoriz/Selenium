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

public class TrajetTourcoingCroixPuis0km {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8090/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testTrajetTourcoingCroixPuis0km() throws Exception {
		selenium.open("/");
		selenium.type("id=departure", "Tourcoing, France");
		selenium.type("id=arrival", "Croix");
		selenium.open("/stopoff/search?date=07%2F04%2F2015+16%3A26&departure.address=Tourcoing%2C+France&departure.latitude=3.1620699999999715&departure.longitude=50.724993&arrival.address=Croix%2C+France&arrival.latitude=3.1487190000000282&arrival.longitude=50.67731999999999#result");
		assertTrue(selenium.isElementPresent("css=h3"));
		selenium.type("id=departure.precision", "0");
		selenium.type("id=arrival.precision", "0");
		selenium.click("css=input.btn.btn-default");
		selenium.waitForPageToLoad("30000");
		assertEquals("Aucun résultat correspond à votre recherche.", selenium.getText("css=i"));
		selenium.click("//input[@value='Chercher']");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=input.btn.btn-default");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
