package com.masson.axel.timer;

public class Timer {
	
	private long PreviousTime;
	
	public Timer()
	{
		setPreviousTime(System.currentTimeMillis());
	}

	public long getPreviousTime() {
		return PreviousTime;
	}

	public void setPreviousTime(long currentTime) {
		PreviousTime = currentTime;
	}
	
	public boolean TimerEvent(int timer)
	{
		if (System.currentTimeMillis() - getPreviousTime() > timer)
		{	ResetTimer();
			return true;
		}
		return false;
	}
	
	public void ResetTimer()
	{
		PreviousTime = System.currentTimeMillis();
	}
	
	public boolean isTimerReady(int timer)
	{
		if (System.currentTimeMillis() - getPreviousTime() > timer)
		{	ResetTimer();
			return true;
		}
		return false;
	}

}
