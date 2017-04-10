package pl.edu.pjwstk.lab6.jbhsel;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on myLogin page")
    public void userOnmyPagePage(){
        pages.myPage().open();
    }

    @When("user clicks the login input field and enter $login")
    public void inputLoginField(String login) {
        pages.myPage().enterLogin(login);
    }
    
    @When("user clicks the password input field and enter $password")
    public void inputPasswordField(String password) {
        pages.myPage().enterPassword(password);
    }
    

    @Then("user click login button and the fact that he is logged is $bool")
    public void Logged(Boolean bool) {
    	Assert.assertEquals(bool, pages.myPage().login());
    }
    
    @When("user change url to access LoggedPage")
    public void loggedPage(){
    	pages.loggedPage().open();
    }
    
    @Then("the fact that user can access this site is false")
    public void canAccess(){
    	Assert.assertFalse(pages.loggedPage().canAccess());
    }
    
    @When("user click $name link user will be logged out")
    public void logout(String name){
    	pages.myPage().click(name);
    }
    
    @Then("User is logged out, and redirected to home page with loggin form - the fact is $bool")
    public void isOnHomePage(Boolean bool){
    	Assert.assertEquals(bool, pages.myPage().hasForm());
    }

}
