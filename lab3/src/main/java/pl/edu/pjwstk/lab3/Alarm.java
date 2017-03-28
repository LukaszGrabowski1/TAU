package pl.edu.pjwstk.lab3;


public interface Alarm {
	Boolean shouldRing(Time time);
	void addAlarmTime(Time time);
	void clearAlarmTime(Time time);
}
