package com.neo82.consistentHash;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String ip;
    private final int virtualNodeCount;

    private final List<VirtualNode> virtualNodes = new ArrayList<>();

    public Node(String ip, int virtualNodeCount) {
        this.ip = ip;
        this.virtualNodeCount = virtualNodeCount;

        for (int i = 0; i < virtualNodeCount; i++) {
            virtualNodes.add(new VirtualNode(ip, i));
        }
    }

    public List<VirtualNode> getVirtualNodes() {
        return this.virtualNodes;
    }
}
