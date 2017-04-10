package pl.edu.pjwstk.lab6.jbhsel;

import org.jbehave.web.selenium.WebDriverProvider;

import pl.edu.pjwstk.lab6.jbhsel.pages.LoggedPage;
import pl.edu.pjwstk.lab6.jbhsel.pages.MyPage;

public class Pages {

    private WebDriverProvider driverProvider;

    private MyPage mypage;
    private LoggedPage loggedPage;

    public Pages(WebDriverProvider driverProvider) {
        super();
        this.driverProvider = driverProvider;
    }

    public MyPage myPage() {
        if (mypage == null) {
            mypage = new MyPage(driverProvider);
        }
        return mypage;
    }
    
    public LoggedPage loggedPage(){
    	if(loggedPage == null){
    		loggedPage = new LoggedPage(driverProvider);
    	}
    	return loggedPage;
    }
}
