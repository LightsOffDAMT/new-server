package com.company.game.mechanics.handlers.server;

import com.company.game.contributed.GameStorage;
import com.company.game.entities.Player;
import com.company.server.states.generators.UniqueIdentifierGenerator;
import com.company.server.users.PlayerInnerIdentifierResolver;
import com.company.server.users.UserService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class BaseConnectListener implements ConnectListener {
    private final PlayerInnerIdentifierResolver resolver;
    private final UniqueIdentifierGenerator identifierGenerator;
    private final GameStorage<ConcurrentHashMap<String, Player>> players;
    private final UserService<SocketIOClient> socketIOClientUserService;

    @Autowired
    public BaseConnectListener(final PlayerInnerIdentifierResolver resolver, final UniqueIdentifierGenerator identifierGenerator, final GameStorage<ConcurrentHashMap<String, Player>> players, final UserService<SocketIOClient> socketIOClientUserService) {
        this.resolver = resolver;
        this.identifierGenerator = identifierGenerator;
        this.players = players;
        this.socketIOClientUserService = socketIOClientUserService;
    }

    @Override
    public void onConnect(final SocketIOClient socketIOClient) {
        final String innerId = identifierGenerator.getId();
        final String outerId = socketIOClient.getSessionId().toString();
        resolver.putResolverPair(outerId, innerId);
        socketIOClientUserService.addUser(innerId, socketIOClient);
        players.storage().put(innerId, new Player().setId(innerId));
    }
}
