package pl.edu.pjwstk.lab3;

import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;



@RunWith(EasyMockRunner.class)
public class AlarmTest {
	
	String now = Time.now().toString();
	
	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@TestSubject
	AlarmImpl alarmImpl = new AlarmImpl();
	
	@Mock
	Alarm alarm;
	
	@Test
	public void alarmRingTest() {
		Time now = Time.now();
		alarm.addAlarmTime(now);
		expect(alarm.shouldRing(now)).andReturn(true);
		replay(alarm);
		alarm.addAlarmTime(now);
		assertTrue(alarm.shouldRing(now));
		verify(alarm);
	}
	
	@Test
	public void alarmNotRingTest(){
		Time time = Time.now();
		Time time1 = time;
		alarm.addAlarmTime(time);
		expect(alarm.shouldRing(time1)).andReturn(false);
		replay(alarm);
		alarm.addAlarmTime(time);
		assertFalse(alarm.shouldRing(time1));
		verify(alarm);
	}
	
	@Test
	public void alarmNotRingTwiceTest(){
		Time time = Time.now();
		alarm.addAlarmTime(time);
		expect(alarm.shouldRing(time)).andReturn(true).andReturn(false);
		replay(alarm);
		assertTrue(alarm.shouldRing(time));
		assertFalse(alarm.shouldRing(time));
	}
}
