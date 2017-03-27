package pl.edu.pjwstk.lab4;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
		alarmImpl.addAlarmTime(now);
		alarmImpl.setCurrentTime(now);
		expect(alarm.shouldRing()).andReturn(true);
		replay(alarm);
		assertTrue(alarm.shouldRing());
		verify(alarm);
	}
	
	
	
	@Test
	public void alarmNotRingTest(){
		Time time = Time.now();
		Time time1 = time;
		alarmImpl.addAlarmTime(time);
		alarmImpl.setCurrentTime(time1);
		expect(alarm.shouldRing()).andReturn(false);
		replay(alarm);
		assertFalse(alarm.shouldRing());
		verify(alarm);
	}
	
	@Test
	public void alarmNotRingTwiceTest(){
		Time time = Time.now();
		alarmImpl.addAlarmTime(time);
		alarmImpl.setCurrentTime(time);
		expect(alarm.shouldRing()).andReturn(true).andReturn(false);
		replay(alarm);
		assertTrue(alarm.shouldRing());
		assertFalse(alarm.shouldRing());
	}
}
