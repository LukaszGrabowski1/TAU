package pl.edu.pjwstk.lab4;


import java.util.ArrayList;
import java.util.List;

public class AlarmImpl implements Alarm {
	
	List<Time> alarms;
	private boolean state = false;
	private Time currentTime;
	
	public AlarmImpl(){
		this.alarms = new ArrayList<Time>();
	};
	
	public AlarmImpl(List<Time> alarms){
		this.alarms = alarms;
	}
	
	public void setCurrentTime(Time currentTime){
		this.currentTime = currentTime;
	}
	
	public void setCurrentTime(String time){
		Time t = new Time();
		String hour;
		String minute;
		int split = time.indexOf(":");
		hour = time.substring(0, split);
		minute = time.substring(split+1, time.length());
		t.setTime(Integer.parseInt(hour), Integer.parseInt(minute));
		this.currentTime = t;
	}
	
	public Time getCurrentTime(){
		return this.currentTime;
	}
	public Boolean shouldRing() {
		Boolean returnValue = false;
		for (Time time : alarms) {
			if(time.getTime().equals(this.currentTime.getTime())){
				if(!this.state){
					returnValue = true;
					this.state = true;
				}else{
					returnValue = false;
				}
			}else{
				this.state = false;
				returnValue = false;
			}
		}
		return returnValue;
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
