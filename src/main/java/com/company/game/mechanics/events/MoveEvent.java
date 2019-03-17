package com.company.game.mechanics.events;

import com.company.server.events.EventType;


public class MoveEvent {
    private String eventType = "MovePlayer";
    private double x;
    private double y;
    private double rotation;

    public double getX() {
        return x;
    }

    public MoveEvent setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public MoveEvent setY(double y) {
        this.y = y;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public MoveEvent setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public double getRotation() {
        return rotation;
    }

    public MoveEvent setRotation(final double rotation) {
        this.rotation = rotation;
        return this;
    }

    @Override
    public String toString() {
        return "MoveEvent{" +
                "eventType='" + eventType + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", rotation=" + rotation +
                '}';
    }
}
