package com.company.game.mechanics.handlers.util;

import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



public class ProxyLoggerEventIncomingHandler extends SimpleEventIncomingHandler<Object> {
    private Logger LOGGER = LoggerFactory.getLogger(ProxyLoggerEventIncomingHandler.class);

    @Override
    public void process0(SocketIOClient client, Object data, AckRequest ackRequest) {
        LOGGER.info("Received '{}' with data: '{}'", data.getClass().getSimpleName(), data.toString());
        next(client, data, ackRequest);
    }
}
