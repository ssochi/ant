package org.usky.ant;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.usky.ant.core.IntSet;

/**
 * 笔试题目2：
 * 给定两个文件，分别有100亿个整数，只提供1G内存，如何找出两文件交集？
 *
 * 思路：
 * 题目中给出 100亿个整数大约需要40G才能存下，因此可以推断出一个整数4byte
 * 那么同样可以使用题目一中的BitMap来存储所有整数。
 * 之后通过BitMap进行查重找出交集。
 *
 * 这里使用随机数的方式替换了文件方式,因为40G文件太大,执行效率过低。
 */
public class UnionMain {
	public static final long _100_0000_0000 =  10000000000L;
	public static void main(String[] args) throws IOException{
		Random r = new Random();
		IntSet whiteList = new IntSet();
		for (long i = 0; i < _100_0000_0000; i++) {
			whiteList.add(r.nextInt());
			printProgress(i);
		}

		try(FileOutputStream osm = new FileOutputStream("union_result")){
			for (long i = 0; i < _100_0000_0000; i++) {
				int val = r.nextInt();
				printProgress(i);
				if (whiteList.contains(val)){
					appendFile(osm,val);
				}
			}
		}
	}

	private static void appendFile(FileOutputStream osm, int val) throws IOException {
		for (int j = 0; j < 4; j++) {
			val = val >> (8 * j);
			osm.write((val & 255));
		}
	}

	private static void printProgress(long t) {
		if (t % 100000 == 0){
			System.out.println(t / 100000000d + " %");
		}
	}
}
