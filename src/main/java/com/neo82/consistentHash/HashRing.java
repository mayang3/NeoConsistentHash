package com.neo82.consistentHash;

import java.util.Map;
import java.util.TreeMap;

public class HashRing {
    private TreeMap<Long, VirtualNode> hashRing = new TreeMap<>();
    private HashFunction hashFunction = new HashFunction();

    public void add(Node node) {
        for (VirtualNode virtualNode : node.getVirtualNodes()) {
            this.add(virtualNode);
        }
    }

    public void add(VirtualNode virtualNode) {
        String key = virtualNode.getKey();
        long hash = hashFunction.hash(key);

        for (int i = 0; i < 10; i++) {
            if (!hashRing.containsKey(hash)) break;
            hash = hashFunction.hash(key + 'a'); // add salt
        }

        if (hashRing.containsKey(hash)) {
            throw new RuntimeException(String.format("Hash Key is duplicated... hash %d", hash));
        }

        hashRing.put(hash, virtualNode);
    }

    public VirtualNode find(String key) {
        if (hashRing.isEmpty()) return null;

        long hash = hashFunction.hash(key);

        Map.Entry<Long, VirtualNode> entry = hashRing.ceilingEntry(hash);

        if (entry == null) return hashRing.firstEntry().getValue();

        return entry.getValue();
    }
}
