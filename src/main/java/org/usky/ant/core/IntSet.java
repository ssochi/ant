package org.usky.ant.core;

public class IntSet {
	// 134217728 = 256 * 256 * 256 * 256 / 32
	private final int[] bitmap = new int[134217728];
	public IntSet(){

	}

	public void add(int ip){
		// 前27位对应offset
		int offset = ip >>> 5;
		// 后5位数据存储在int中
		int bit = ip & 31;
		bitmap[offset] |= (1 << bit);
	}

	public boolean contains(int ip){
		int offset = ip >>> 5;
		int bit = ip & 31;
		return ((bitmap[offset] >> bit) & 1) == 1;
	}
}


