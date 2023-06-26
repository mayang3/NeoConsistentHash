package com.neo82.consistentHash;

public class Data {
    private final String key;
    private final String value;

    public Data(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
