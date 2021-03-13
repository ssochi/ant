package org.usky.ant.core;

public class IpSet {
    private final IntSet intSet = new IntSet();
    public void add(String ip){
        intSet.add(IPUtil.format(ip));
    }

    public boolean contains(String ip){
        return intSet.contains(IPUtil.format(ip));
    }
}
