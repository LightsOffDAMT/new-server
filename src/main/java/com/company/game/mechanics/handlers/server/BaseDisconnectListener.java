package com.company.game.mechanics.handlers.server;

import com.company.game.contributed.GameStorage;
import com.company.game.entities.Player;
import com.company.server.broadcast.ResponseService;
import com.company.server.users.PlayerInnerIdentifierResolver;
import com.company.server.users.SocketIOClientUserService;
import com.company.server.users.UserService;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class BaseDisconnectListener implements DisconnectListener {
    UserService<SocketIOClient> socketIOClientUserService;
    PlayerInnerIdentifierResolver resolver;
    GameStorage<ConcurrentHashMap<String, Player>> players;
    ResponseService responseService;

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        String innerId = resolver.getInnerId(socketIOClient.getSessionId().toString());
        responseService.submitBroadcastOperation(new Payload<String>().setEventName("DISCONNECT_PLAYER").setPayload(innerId));
        socketIOClientUserService.disconnectUser(innerId);
        players.storage().remove(innerId);
    }

    @Autowired
    public BaseDisconnectListener setSocketIOClientUserService(final UserService<SocketIOClient> socketIOClientUserService) {
        this.socketIOClientUserService = socketIOClientUserService;
        return this;
    }

    @Autowired
    public BaseDisconnectListener setResolver(final PlayerInnerIdentifierResolver resolver) {
        this.resolver = resolver;
        return this;
    }

    @Autowired
    public BaseDisconnectListener setPlayers(final GameStorage<ConcurrentHashMap<String, Player>> players) {
        this.players = players;
        return this;
    }

    @Autowired
    public BaseDisconnectListener setResponseService(final ResponseService responseService) {
        this.responseService = responseService;
        return this;
    }
}
