package br.com.vanhackathon.axiomzen.mastermind.util;

import java.util.Random;

public class RandomUtil {
	public static Long generateRandom() {
		Random random = new Random(DateUtil.getActualTimeInMillis());
		
		return random.nextLong();
	}
}
