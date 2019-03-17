package com.company.server.util;

public class IdentifyablePayload<T>{
    private String id;
    private Payload<T> payload;

    public IdentifyablePayload(){}

    public String getId() {
        return id;
    }

    public IdentifyablePayload<T> setId(String id) {
        this.id = id;
        return this;
    }

    public Payload<T> getInnerPayload() {
        return payload;
    }

    public IdentifyablePayload<T> setPayload(Payload<T> payload) {
        this.payload = payload;
        return this;
    }

    @Override
    public String toString() {
        return "IdentifyablePayload{" +
                "id='" + id + '\'' +
                ", payload=" + payload +
                '}';
    }
}
