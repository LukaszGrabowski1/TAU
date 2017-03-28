package pl.edu.pjwstk.lab3;


import java.util.ArrayList;
import java.util.List;

public class AlarmImpl implements Alarm {
	
	private boolean state;
	List<Time> alarms;
	private Time previous;
	
	public AlarmImpl(){
		this.alarms = new ArrayList<Time>();
	};
	public AlarmImpl(List<Time> alarms){
		this.alarms = alarms;
	}
	
	public Boolean shouldRing(Time currentTime) {
		for (Time time : alarms) {
			if(time == currentTime && this.previous != currentTime){
					previous = currentTime;
					return true;
				}
				else{
					return false;
			}
		}
		this.previous = null;
		return false;
	}
		
	public void addAlarmTime(Time time) {
		this.alarms.add(time);
	}

	public void clearAlarmTime(Time time) {
		this.alarms.remove(time);
	}
	
	public List<Time> getAlarms(){
		return this.alarms;
	}
	
	public void removeAllAlarms(){
		this.alarms = null;
		this.alarms = new ArrayList<Time>();
	}
}
