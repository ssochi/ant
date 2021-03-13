package org.usky.ant;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.usky.ant.ip.IntSet;

public class UnionMain {
	public static final long _100_0000_0000 =  10000000000L;
	public static void main(String[] args) throws IOException{
		Random r = new Random();
		IntSet whiteList = new IntSet();
		for (long i = 0; i < _100_0000_0000; i++) {
			whiteList.add(r.nextInt());
			if (i % 100000 == 0){
				System.out.println("add : " + i / 100000000d + " %");
			}
		}

		try(FileOutputStream osm = new FileOutputStream("union_result")){
			for (long i = 0; i < _100_0000_0000; i++) {
				int val = r.nextInt();
				if (i % 100000 == 0){
					System.out.println("contains : " + i / 100000000d + " %");
				}
				if (whiteList.contains(val)){
					for (int j = 0; j < 4; j++) {
						val = val >> (8 * j);
						osm.write((val & 255));
					}
				}
			}
		}
	}
}
