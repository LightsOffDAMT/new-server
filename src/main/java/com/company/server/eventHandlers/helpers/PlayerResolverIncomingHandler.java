package com.company.server.eventHandlers.helpers;

import com.company.game.contributed.PlayerGameStorage;
import com.company.game.entities.Player;
import com.company.server.dto.wrappers.EventWithPlayerWrapper;
import com.company.server.eventHandlers.pipeline.SimpleEventIncomingHandler;
import com.company.server.users.SocketIOClientPlayerIdentifierResolver;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerResolverIncomingHandler extends SimpleEventIncomingHandler<Object> {
    final SocketIOClientPlayerIdentifierResolver resolver;
    final PlayerGameStorage playerGameStorage;


    @Autowired
    public PlayerResolverIncomingHandler(SocketIOClientPlayerIdentifierResolver resolver, PlayerGameStorage playerGameStorage) {
        this.resolver = resolver;
        this.playerGameStorage = playerGameStorage;
    }

    @Override
    public void process0(SocketIOClient client, Object data, AckRequest ackRequest) {
        String playerId = resolver.storage().get(client.getSessionId());
        Player player = null;
        for (Player curPlayer : playerGameStorage.storage()) {
            if (curPlayer.getId().equals(playerId)) {
                player = curPlayer;
            }
        }
        if (player != null) {
            EventWithPlayerWrapper eventWithPlayerWrapper = new EventWithPlayerWrapper(player, data);
            process0(client, eventWithPlayerWrapper, ackRequest);

            //player.position.moveToPoint(data.getX(), data.getY());
        }
    }
}
