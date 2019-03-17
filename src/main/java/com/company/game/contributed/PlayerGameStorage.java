package com.company.game.contributed;

import com.company.game.entities.Player;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


public class PlayerGameStorage implements GameStorage<ConcurrentHashMap<String, Player>> {
    private ConcurrentHashMap<String, Player> storage = new ConcurrentHashMap<String, Player>();

    @Override
    public ConcurrentHashMap<String, Player> storage() {
        return storage;
    }
}
