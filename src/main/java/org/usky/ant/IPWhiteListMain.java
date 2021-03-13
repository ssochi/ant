package org.usky.ant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.usky.ant.core.*;

/**
 * 笔试题目1：
 * 随机生成一个大文件(比如1G)，文件中存储的内容是IPV4地址；
 * 然后需要实现一个IP地址搜索的功能，输入任意一个IP地址判断该IP地址是否存在文件中。
 *
 * 思路：
 * 一个IPv4地址共32位，可以转换为一个Int。
 * Int一共有2^32个,如果要存下所有Int需要4G内存。
 * 如果按位存储(BitMap)，那么实际消耗的内存大小为2^32 / 8，
 * 等于512M,完全可以存入。
 *
 * 使用BitMap写入时间复杂度为O(1)，查询的时间复杂度也为O(1)
 * 因此不存在性能问题。
 *
 */
public class IPWhiteListMain {
	public static void main(String[] args) throws IOException {
		// 第一步 准备文件
		System.out.println("generate file ...");
		IPFileGenerator fileGenerator = new IPFileGenerator();
		fileGenerator.generate("ip_list", 1024 * 1024 * 1024);

		// 第二步 构建IP白名单,也就是构建BitMap
		System.out.println("build white list ...");
		IpSet whiteList = new IpSet();
		try(FileInputStream ism = new FileInputStream("ip_list")){
			IPReader ipReader = new IPReader(ism);
			ipReader.foreach(whiteList::add);
		}

		// 第三步 准备测试数据
		System.out.println("prepare data ...");
		List<String> randIpStr = new LinkedList<>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			randIpStr.add(IPUtil.randIpStr(r));
		}

		// 第四步 预热,提前JIT
		System.out.println("warm up ...");
		for (int i = 0; i < 1000; i++) {
			for (String ip : randIpStr) {
				whiteList.contains(ip);
			}
		}

		// 第五步 对每个数据进行测试
		final long t = System.nanoTime();
		for (String ip : randIpStr) {
			final boolean result = whiteList.contains(ip);
			System.out.printf("cost = %s ns, arg = %s, result = %s%n",
					System.nanoTime() - t,ip,result);
		}
	}
}
