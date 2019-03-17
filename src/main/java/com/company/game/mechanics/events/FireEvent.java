package com.company.game.mechanics.events;

import com.company.game.abilities.SimpleMovablePoint;


public class FireEvent {
    private String id;
    private SimpleMovablePoint position;

    public String getId() {
        return id;
    }

    public FireEvent setId(String id) {
        this.id = id;
        return this;
    }

    public SimpleMovablePoint getPosition() {
        return position;
    }

    public FireEvent setPosition(SimpleMovablePoint position) {
        this.position = position;
        return this;
    }

    @Override
    public String toString() {
        return "FireEvent{" +
                "id='" + id + '\'' +
                ", position=" + position +
                '}';
    }
}
