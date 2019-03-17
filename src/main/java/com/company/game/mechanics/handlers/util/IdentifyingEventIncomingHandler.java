package com.company.game.mechanics.handlers.util;

import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.company.server.users.PlayerInnerIdentifierResolver;
import com.company.server.util.IdentifyablePayload;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



public class IdentifyingEventIncomingHandler extends SimpleEventIncomingHandler<Payload> {
    private PlayerInnerIdentifierResolver resolver;

    @Override
    public void process0(SocketIOClient client, Payload data, AckRequest ackRequest) {
        String playerInnerId = resolver.getInnerId(client.getSessionId().toString());
        if(playerInnerId != null){
            IdentifyablePayload payload = new IdentifyablePayload();
            payload.setPayload(data);
            payload.setId(playerInnerId);
            next(client, payload, ackRequest);
        } else {
            client.disconnect();
        }
    }

    @Autowired
    public IdentifyingEventIncomingHandler setResolver(PlayerInnerIdentifierResolver resolver) {
        this.resolver = resolver;
        return this;
    }
}
