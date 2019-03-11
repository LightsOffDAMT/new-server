package com.company.game.contributed;

import com.company.game.entities.Player;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;


@Component
public class PlayerGameStorage implements GameStorage<ConcurrentSkipListSet<Player>> {
    private ConcurrentSkipListSet<Player> storage = new ConcurrentSkipListSet<>(Comparator.comparing(Player::getId));

    @Override
    public ConcurrentSkipListSet<Player> storage() {
        return storage;
    }
}
