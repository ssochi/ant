package org.usky.ant.ip;

import java.util.Random;

public class IPUtil {
	public static String randIpStr(Random r) {
		return r.nextInt(256) + "." + r.nextInt(256)
				+ "." + r.nextInt(256) + "." + r.nextInt(256);
	}

	public static int format(String ip) {
		String[] split = ip.split("\\.");
		int res = 0;
		for (int i = 0; i < split.length; i++) {
			int $8bit = Integer.parseInt(split[i]);
			res |= ($8bit << (i * 8));
		}
		return res;
	}
}
