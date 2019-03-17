package com.company.server.users;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class SocketIOClientUserService implements UserService<SocketIOClient> {
    private final ConcurrentHashMap<String, SocketIOClient> clients = new ConcurrentHashMap<>();

    @Override
    public Optional<SocketIOClient> getUser(final String id) {
        return Optional
                .ofNullable(clients.get(id))
                .map((socketIOClient -> {
                    if(socketIOClient.isChannelOpen())
                        return socketIOClient;
                    else
                        return null;
                }));
    }

    @Override
    public void addUser(final String id, final SocketIOClient user) {
        clients.put(id, user);
    }

    @Override
    public void disconnectUser(String id) {
        clients.get(id).disconnect();
    }
}
