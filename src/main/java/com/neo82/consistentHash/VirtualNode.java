package com.neo82.consistentHash;

public class VirtualNode {
    private final String ip;
    private final int virtualNumber;

    public VirtualNode(String ip, int virtualNumber) {
        this.ip = ip;
        this.virtualNumber = virtualNumber;
    }

    public String getKey() {
        return ip + "_" + virtualNumber;
    }

    public String getIp() {
        return ip;
    }

    public int getVirtualNumber() {
        return virtualNumber;
    }
}
