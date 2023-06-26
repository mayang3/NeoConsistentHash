package com.neo82.consistentHash;

import java.util.HashMap;
import java.util.Map;

public class Console {
    private static HashRing hashRing = new HashRing();
    private static Map<String, Integer> dataMap = new HashMap<>();

    public void configure() {
        hashRing.add(new Node("10.12.34.56", 30));
        hashRing.add(new Node("20.34.56.78", 30));
        hashRing.add(new Node("30.34.56.78", 30));
        hashRing.add(new Node("40.34.56.78", 30));
        hashRing.add(new Node("50.34.56.78", 30));
    }

    public void setData() {
        for (int i = 0; i < 100000; i++) {
            String key = "key_" + i;
            String value = "value_" + i;

            VirtualNode virtualNode = hashRing.find(key);

            dataMap.merge(virtualNode.getIp(), 1, Integer::sum);
        }
    }

    public static void main(String[] args) {
        Console console = new Console();
        console.configure();
        console.setData();

        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            System.out.println("IP : " + entry.getKey() + ", Count : " + entry.getValue());
        }
    }
}
