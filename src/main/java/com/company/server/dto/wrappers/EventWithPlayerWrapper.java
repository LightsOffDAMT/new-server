package com.company.server.dto.wrappers;

import com.company.game.entities.Player;

public class EventWithPlayerWrapper {
    private Player player;
    private Object payload;

    public Player getPlayer() {
        return player;
    }

    public Player withPlayer(Player player) {
        return this.player = player;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public EventWithPlayerWrapper(Player player, Object payload) {
        this.player = player;
        this.payload = payload;
    }
}
