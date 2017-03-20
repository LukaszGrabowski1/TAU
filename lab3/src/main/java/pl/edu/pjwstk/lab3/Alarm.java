package pl.edu.pjwstk.lab3;

import java.time.LocalTime;

public interface Alarm {
	Boolean shouldRing(LocalTime time);
	void addAlarmTime(LocalTime time);
	void clearAlarmTime(LocalTime time);
}
