package pl.edu.pjwstk.lab4;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TimeTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @TestSubject
    public AlarmImpl alarm = new AlarmImpl();

    @Mock
    public Time time;

    @Test
    public void shouldRingTest(){
        Time t = new Time();
        t.setTime(11,15);
        alarm.addAlarmTime(t);
        expect(time.getTime())
        		.andReturn("11:15")
                .andReturn("11:15")
                .andReturn("11:30")
                .andReturn("11:15");
        replay(time);
        assertEquals(true, alarm.shouldRing());
        assertEquals(false, alarm.shouldRing());
        assertEquals(false, alarm.shouldRing());
        assertEquals(true, alarm.shouldRing());
        verify(time);
    }


    @Test
    public void clearRingTest(){
        Time t = new Time();
        t.setTime(11, 15);
        alarm.addAlarmTime(t);
        expect(time.getTime()).andReturn("11:15").anyTimes();
        replay(time);
        assertEquals(true, alarm.shouldRing());
        alarm.clearAlarmTime(t);
        assertEquals(false, alarm.shouldRing());
        verify(time);
    }
}