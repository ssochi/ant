package org.usky.ant;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.usky.ant.ip.IPUtil;
import org.usky.ant.ip.IPWhiteList;

public class TIPWhiteList {
	@Test
	public void IPWhiteList() throws Throwable {
		IPWhiteList ipWhiteList = new IPWhiteList();
		Random rand = new Random();
		int ip = 0;
		try{
			for (int i = 0; i < 99999999; i++) {
				ip = rand.nextInt();
				ipWhiteList.add(ip);
				if (!ipWhiteList.contains(ip)){
					System.out.println("error" + ip);
				}
			}
		}catch (Throwable throwable){
			System.out.println(ip);
			throw throwable;
		}
	}

	@Test
	public void IPWhiteListSinge(){
		IPWhiteList ipWhiteList = new IPWhiteList();
		int ip = 2146986841;
		ipWhiteList.add(ip);
	}

	public static final int BLACK_TEST_DATA_GROUP_SIZE = 1000000;
	@Test
	public void IPWhiteListBlack(){
		Random r = new Random();
		Set<String> data = new HashSet<String>();
		Set<String> black = new HashSet<String>();
		while (data.size() < BLACK_TEST_DATA_GROUP_SIZE){
			data.add(IPUtil.randIpStr(r));
		}
		while (black.size() < BLACK_TEST_DATA_GROUP_SIZE){
			String val = IPUtil.randIpStr(r);
			if (!data.contains(val)){
				black.add(val);
			}
		}

		IPWhiteList whiteList = new IPWhiteList();
		for (String datum : data) {
			whiteList.add(datum);
		}

		for (String datum : data) {
			if (!whiteList.contains(datum)){
				System.out.println("white-err" + datum);
			}
		}

		for (String datum : black) {
			if (whiteList.contains(datum)){
				System.out.println("black-err" + datum);
			}
		}

	}
}
