package pl.edu.pjwstk.lab6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

@FixMethodOrder(MethodSorters.JVM)
public class SiteTest {

	private static PhantomJSDriver driver;
	WebElement element;
	String login = "1234";

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"/tmp/phantomjs"
		);
		driver = new PhantomJSDriver(caps);
	}

	@Test
	public void loginFormExistTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		element = driver.findElement(By.tagName("form"));
		assertNotNull(element);
	}
	
	@Test
	public void loginFormSubmitTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		element = driver.findElement(By.name("zaloguj"));
		assertNotNull(element);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);
	    try {
			FileUtils.copyFile(screenshot, new File("./target/screenshots/LoginPage.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void notLoggedTest(){
		Cookie login = driver.manage().getCookieNamed("login");
		Cookie logged = driver.manage().getCookieNamed("logged");
		String url = driver.getCurrentUrl();
		assertTrue(driver.manage().getCookies().size()<=1); // 1 - autogenerowany cookie z ID sesji
		assertNull(login);
		assertNull(logged);
		assertFalse(url.contains("logged"));
	}
	
	@Test
	public void loginInorrectTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		WebElement loginInput = driver.findElement(By.name("login"));
		WebElement passInput = driver.findElement(By.name("password"));
		element = driver.findElement(By.name("zaloguj"));
		loginInput.sendKeys(login+"553535");
		passInput.sendKeys("wrongPass");
	
		assertEquals(login+"553535", loginInput.getAttribute("value"));
		assertEquals("wrongPass", passInput.getAttribute("value"));
		element.click();
	
		Boolean logged = driver.getCurrentUrl().contains("logged");
		assertFalse(logged);
		
		Boolean log = driver.getPageSource().contains("Zalogowany jako "+login);
		assertFalse(log);
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);
	    try {
			FileUtils.copyFile(screenshot, new File("./target/screenshots/LoginIncorect.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void loginCorrectTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		WebElement loginInput = driver.findElement(By.name("login"));
		WebElement passInput = driver.findElement(By.name("password"));
		element = driver.findElement(By.name("zaloguj"));
		
		loginInput.sendKeys(login);
		passInput.sendKeys("5678");
		
		assertEquals(login, loginInput.getAttribute("value"));
		assertEquals("5678", passInput.getAttribute("value"));
		element.click();
		
		Boolean logged = driver.getCurrentUrl().contains("logged");
		assertTrue(logged);
		
		Boolean log = driver.getPageSource().contains("Zalogowany jako "+login);
		assertTrue(log);
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);
	    try {
			FileUtils.copyFile(screenshot, new File("./target/screenshots/LoginCorrect.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void logoutTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		element = driver.findElement(By.linkText("Wyloguj się"));
		assertNotNull(element);
		element.click();
		String html = driver.getPageSource();
		Boolean logged = driver.getCurrentUrl().contains("logged");
		assertFalse(logged);
		assertTrue(html.contains("Wylogowano pomyślnie"));
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    assertNotNull(screenshot);
	    try {
			FileUtils.copyFile(screenshot, new File("./target/screenshots/Logout.png"));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	    
	}

	
	@Test
	public void jQueryTest(){
		driver.get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
		element = (WebElement) ((JavascriptExecutor)driver).executeScript("return $('.img')[0]");
		assertNull(element);
	}


	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
