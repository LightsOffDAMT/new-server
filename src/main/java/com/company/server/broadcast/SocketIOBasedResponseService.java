package com.company.server.broadcast;

import com.company.server.users.SocketIOClientUserService;
import com.company.server.users.UserService;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;


public class SocketIOBasedResponseService implements ResponseService {
    private Logger LOGGER = LoggerFactory.getLogger(SocketIOBasedResponseService.class);
    private SocketIOServer server;
    private UserService<SocketIOClient> userService;

    @Override
    public void submitBroadcastOperation(Payload payload) {
        LOGGER.trace("Submitted broadcast operation with data:'{}", payload);
        server.getBroadcastOperations().sendEvent(payload.getEventName(), payload.getPayload());
    }

    @Override
    public void submitSingleResponse(String destination, Payload payload) {
        LOGGER.trace("Submitted response to {} with data:'{}'", destination, payload);
        userService.getUser(destination)
        .ifPresent(
                socketIOClient -> socketIOClient.sendEvent(payload.getEventName(), payload.getPayload())
        );

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
