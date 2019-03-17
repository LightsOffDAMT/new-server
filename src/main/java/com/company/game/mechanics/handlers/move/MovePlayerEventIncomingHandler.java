package com.company.game.mechanics.handlers.move;

import com.company.game.contributed.GameStorage;
import com.company.game.entities.Player;
import com.company.game.mechanics.events.MovePlayerEvent;
import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;



public class MovePlayerEventIncomingHandler extends SimpleEventIncomingHandler<MovePlayerEvent> {
    private Logger LOGGER = LoggerFactory.getLogger(MovePlayerEventIncomingHandler.class);
    private GameStorage<ConcurrentHashMap<String, Player>> players;

    @Override
    public void process0(SocketIOClient client, MovePlayerEvent data, AckRequest ackRequest) {
        Player player = players.storage().get(data.getPlayerId());
        if(player != null){
            player.position.moveToPoint(data.getX(), data.getY());
            player.position.setRotation(data.getRotation());
        } else {
            LOGGER.error("Player with id:'{}' is absent in system, handling stops", data.getPlayerId());
            return;
        }
    }

    @Autowired
    public MovePlayerEventIncomingHandler setPlayers(GameStorage<ConcurrentHashMap<String, Player>> players) {
        this.players = players;
        return this;
    }
}
