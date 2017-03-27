package pl.edu.pjwstk.lab4;

import java.util.Calendar;

public class Time {
	private int hour;
	private int minute;
	

	public String getTime(){
		return hour + ":" + minute;
	}
	
	public void setTime(int hour, int minute){
		if(hour < 0 || hour > 23 || minute > 59 || minute < 0)
			throw new IllegalArgumentException();
		else{
			this.hour = hour;
			this.minute = minute;
		}
	}
	
	public void setTime(String time){
		Time t = new Time();
		int split = time.indexOf(":");
		String hour = time.substring(0, split);
		String minute = time.substring(split+1, time.length());
		this.hour = Integer.parseInt(hour);
		this.minute = Integer.parseInt(minute);
	}
	
	
	public static Time now(){
		Time t = new Time();
		Calendar cal = Calendar.getInstance();
		t.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
		return t;
	}
	
	@Override
	public String toString() {
		return this.hour + ":" + this.minute;
	}
}
