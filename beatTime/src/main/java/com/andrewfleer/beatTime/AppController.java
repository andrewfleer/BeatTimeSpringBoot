package com.andrewfleer.beatTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	private static double BEAT_TIME_DIVISOR = 86.4;
	
	@RequestMapping("/")
	public String index() {
		String realTime = "The normal time is: " + getRealTime();
		String beatTime = "The @BeatTime is: @" + getBeatTime();
		return realTime + "\r\n" + beatTime;
	}
	
	public String getBeatTime() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
		
		// Beat time is the following
		// "now" seconds +
		// "now" minutes in seconds +
		// "now" hours in seconds
		// / 86.4
		int seconds = now.get(Calendar.SECOND);
		int minutes = now.get(Calendar.MINUTE) * 60;
		int hours = now.get(Calendar.HOUR) * 60 * 60;
		
		BigDecimal beatSeconds = new BigDecimal((seconds + minutes + hours) / BEAT_TIME_DIVISOR).setScale(2, RoundingMode.HALF_UP);
		
		return beatSeconds.toString();
	}
	
	public String getRealTime() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		
		return df.format(now.getTime());
	}
	
	
}