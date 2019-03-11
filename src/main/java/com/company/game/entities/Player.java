package com.company.game.entities;

import com.company.game.abilities.SimpleMovablePoint;


public class Player {
    public final SimpleMovablePoint position = new SimpleMovablePoint();
    private String id;

    public String getId() {
        return id;
    }

    public Player setId(final String id) {
        this.id = id;
        return this;
    }


}
