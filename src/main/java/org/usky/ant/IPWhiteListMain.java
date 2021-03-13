package org.usky.ant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.usky.ant.ip.IPFileGenerator;
import org.usky.ant.ip.IPReader;
import org.usky.ant.ip.IPUtil;
import org.usky.ant.ip.IntSet;


public class IPWhiteListMain {
	public static void main(String[] args) throws IOException {
		System.out.println("generate file ...");
		IPFileGenerator fileGenerator = new IPFileGenerator();
		fileGenerator.generate("ip_list", 1024 * 1024 * 1024);

		System.out.println("build white list ...");
		IntSet whiteList = new IntSet();
		try(FileInputStream ism = new FileInputStream("ip_list")){
			IPReader ipReader = new IPReader(ism);
			ipReader.foreach(whiteList::add);
		}

		System.out.println("prepare data ...");
		List<String> randIpStr = new LinkedList<>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			randIpStr.add(IPUtil.randIpStr(r));
		}

		System.out.println("warm up ...");
		for (int i = 0; i < 1000; i++) {
			for (String ip : randIpStr) {
				whiteList.contains(ip);
			}
		}

		final long t = System.nanoTime();
		for (String ip : randIpStr) {
			final boolean result = whiteList.contains(ip);
			System.out.printf("cost = %s ns, arg = %s, result = %s%n",
					System.nanoTime() - t,ip,result);
		}
	}
}
