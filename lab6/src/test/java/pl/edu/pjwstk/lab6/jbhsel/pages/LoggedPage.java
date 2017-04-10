package pl.edu.pjwstk.lab6.jbhsel.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class LoggedPage extends WebDriverPage {

    public LoggedPage(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12735/logowanie/logged.php");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }
    
    public Boolean canAccess(){
    	System.out.println(getPageSource().contains("Zalogowany jako"));
    	return false;
    }

}
