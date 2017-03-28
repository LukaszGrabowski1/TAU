package pl.edu.pjwstk.lab4;

import static org.junit.Assert.assertEquals;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
public class RingSteps {
    AlarmImpl alarm;
    Time time;
    
    private String convertTime(String time){
		int split = time.indexOf(":");
		String hour = time.substring(0, split);
		String minute = time.substring(split+1, time.length());
    	return Integer.parseInt(hour)+":"+Integer.parseInt(minute);
    }

    @Given("A sleepy student sets alarm at $alarm to wake up to school")
    public void setAlarmClock(String alarm){
        this.alarm = new AlarmImpl();
        time = mock(Time.class);
        given(time.getTime()).willReturn(convertTime(alarm));
        this.alarm.addAlarmTime(time);
    }

    @When("It would be $time when alarm start to ring")
    public void setCurrentTime(String time){
        this.alarm.setCurrentTime(time);
    }

    @Then("The sleepy student is awake is $result")
    public void checkIfStudentIsAwake(Boolean result){
        assertEquals(result, this.alarm.shouldRing());
    }

}