package org.usky.ant;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.usky.ant.ip.IPUtil;
import org.usky.ant.ip.IntSet;

public class TIPWhiteList {
	@Test
	public void IPWhiteList() throws Throwable {
		IntSet intSet = new IntSet();
		Random rand = new Random();
		int ip = 0;
		try{
			for (int i = 0; i < 99999999; i++) {
				ip = rand.nextInt();
				intSet.add(ip);
				if (!intSet.contains(ip)){
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
		IntSet intSet = new IntSet();
		int ip = 2146986841;
		intSet.add(ip);
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

		IntSet whiteList = new IntSet();
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
