package com.company.game.mechanics;

import com.company.game.contributed.GameStorage;
import com.company.game.entities.Player;
import com.company.server.broadcast.ResponseService;
import com.company.server.util.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.ConcurrentSkipListSet;


public class GameCycle implements Runnable {
    private ResponseService responseService;
    private GameStorage<ConcurrentSkipListSet<Player>> players;

    @Override
    public void run() {
        if(!players.storage().isEmpty())
            responseService.submitBroadcastOperation(
                    new Payload<ConcurrentSkipListSet<Player>>()
                            .setPayload(players.storage())
                            .setEventName("UPDATE_PLAYERS")
            );
    }

    public ResponseService getResponseService() {
        return responseService;
    }
    @Autowired
    public GameCycle setResponseService(final ResponseService responseService) {
        this.responseService = responseService;
        return this;
    }

    public GameStorage<ConcurrentSkipListSet<Player>> getPlayers() {
        return players;
    }
    @Autowired
    @Qualifier("players")
    public GameCycle setPlayers(final GameStorage<ConcurrentSkipListSet<Player>> players) {
        return this;
    }
}
