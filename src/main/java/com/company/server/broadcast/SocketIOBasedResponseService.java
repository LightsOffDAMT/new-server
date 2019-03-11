package com.company.server.broadcast;

import com.company.server.users.UserService;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;


public class SocketIOBasedResponseService implements ResponseService {
    private SocketIOServer server;
    private UserService<SocketIOClient> userService;

    @Override
    public void submitBroadcastOperation(Payload payload) {
        Mono.fromRunnable(()->
                server.getBroadcastOperations().sendEvent(payload.getEventName(), payload.getPayload())
        ).subscribe();
    }

    @Override
    public void submitSingleResponse(String destination, Payload payload) {
        Mono.fromRunnable(()->
                userService.getUser(destination)
                .ifPresent(
                        socketIOClient -> socketIOClient.sendEvent(payload.getEventName(), payload.getPayload())
                )
        ).subscribe();
    }

    public SocketIOServer getServer() {
        return server;
    }
    @Autowired
    public SocketIOBasedResponseService setServer(final SocketIOServer server) {
        this.server = server;
        return this;
    }
    @SuppressWarnings("unused")
    public UserService<SocketIOClient> getUserService() {
        return userService;
    }
    @Autowired
    public SocketIOBasedResponseService setUserService(final UserService<SocketIOClient> userService) {
        this.userService = userService;
        return this;
    }
}
