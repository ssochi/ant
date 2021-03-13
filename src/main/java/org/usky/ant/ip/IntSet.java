package org.usky.ant.ip;

public class IntSet {
	private final int[] bitmap = new int[134217728];
	public IntSet(){

	}
	public void add(String ipStr){
		int ip = IPUtil.format(ipStr);
		add(ip);
	}

	public void add(int ip){
		int offset = ip >>> 5;
		int bit = ip & 31;
		bitmap[offset] |= (1 << bit);
	}

	public boolean contains(String ipStr){
		int ip = IPUtil.format(ipStr);
		return contains(ip);
	}

	public boolean contains(int ip){
		int offset = ip >>> 5;
		int bit = ip & 31;
		return ((bitmap[offset] >> bit) & 1) == 1;
	}
}


