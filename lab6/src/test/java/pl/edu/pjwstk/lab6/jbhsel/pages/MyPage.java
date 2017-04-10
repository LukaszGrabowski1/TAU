package pl.edu.pjwstk.lab6.jbhsel.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MyPage extends WebDriverPage {

    public MyPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void click(String linkText) {
        WebElement e = findElement(By.partialLinkText(linkText));
        System.out.println(e);
        e.click();
    }

    public String getClassesForLink(String linkText) {
        WebElement e = findElement(By.partialLinkText(linkText));
        return e.getAttribute("class");
    }
    
    public void enterLogin(String login){
    	findElement(By.name("login")).sendKeys(login);
    }
    
    public void enterPassword(String password){
    	findElement(By.name("password")).sendKeys(password);
    }
    
    public Boolean login(){
    	findElement(By.name("zaloguj")).click();
    	System.out.println(getPageSource().contains("Zalogowany"));
    	return getCurrentUrl().contains("logged");
    }
    
    public Boolean hasForm(){
    	WebElement e = findElement(By.tagName("form"));
    	if(e.isDisplayed() || !e.equals(null)){
    		return true;
    	}
    	else return false;
    }
}
