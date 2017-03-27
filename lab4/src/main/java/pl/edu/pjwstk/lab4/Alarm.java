package pl.edu.pjwstk.lab4;



public interface Alarm {
	Boolean shouldRing();
	void addAlarmTime(Time time);
	void clearAlarmTime(Time time);
	public void setCurrentTime(String time);
	public void setCurrentTime(Time currentTime);
}
