package br.com.vanhackathon.axiomzen.mastermind.util;

import java.util.Date;

public class DateUtil {

	public static Long getActualTimeInMillis() {
		Date date = new Date();
		return date.getTime();
	}
}
