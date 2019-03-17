package com.company.game.mechanics.events;

import com.company.server.events.EventType;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class MovePlayerEvent extends MoveEvent {
    @JsonIgnore
    private String playerId;

    public MovePlayerEvent(final String playerId, final double x, final double y, final double rotation) {
        super.setX(x);
        super.setY(y);
        super.setRotation(rotation);
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public MovePlayerEvent setPlayerId(String playerId) {
        this.playerId = playerId;
        return this;
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public String getEventType() {
        return super.getEventType();
    }

    @Override
    public MoveEvent setEventType(final String eventType) {
        return super.setEventType(eventType);
    }

    @Override
    public double getRotation() {
        return super.getRotation();
    }

    @Override
    public MoveEvent setRotation(double rotation) {
        return super.setRotation(rotation);
    }

    @Override
    public String toString() {
        return "MovePlayerEvent{" +
                "playerId='" + playerId + '\'' +
                 ",super=" + super.toString() +
                '}';
    }
}
