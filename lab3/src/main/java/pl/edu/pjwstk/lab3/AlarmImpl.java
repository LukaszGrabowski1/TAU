package pl.edu.pjwstk.lab3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AlarmImpl implements Alarm {
	
	private boolean state;
	List<LocalTime> alarms;
	private LocalTime previous;
	
	public AlarmImpl(){
		this.alarms = new ArrayList<LocalTime>();
	};
	public AlarmImpl(List<LocalTime> alarms){
		this.alarms = alarms;
	}
	
	public Boolean shouldRing(LocalTime currentTime) {
		for (LocalTime time : alarms) {
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
		
	public void addAlarmTime(LocalTime time) {
		this.alarms.add(LocalTime.of(time.getHour(), time.getMinute()));
	}

	public void clearAlarmTime(LocalTime time) {
		this.alarms.remove(LocalTime.of(time.getHour(), time.getMinute()));
	}
	
	public List<LocalTime> getAlarms(){
		return this.alarms;
	}
	
	public void removeAllAlarms(){
		this.alarms = null;
		this.alarms = new ArrayList<LocalTime>();
	}
}
