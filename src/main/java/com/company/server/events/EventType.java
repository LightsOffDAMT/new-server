package com.company.server.events;

public enum EventType {
    AUTHORIZE("Authorize"), MOVE_PLAYER("MovePlayer");

    private String string;

    EventType(final String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
