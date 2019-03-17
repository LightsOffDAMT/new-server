package com.company.server.util;

public class Payload<T> {
    private String eventName = "";
    private T payload;

    public String getEventName() {
        return eventName;
    }

    public Payload<T> setEventName(final String eventName) {
        this.eventName = eventName;
        return this;
    }

    public T getPayload() {
        return payload;
    }

    public Payload<T> setPayload(final T payload) {
        this.payload = payload;
        return this;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "eventName='" + eventName + '\'' +
                ", payload=" + payload +
                '}';
    }
}
