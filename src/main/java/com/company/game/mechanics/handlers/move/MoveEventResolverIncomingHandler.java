package com.company.game.mechanics.handlers.move;

import com.company.game.mechanics.events.MoveEvent;
import com.company.game.mechanics.events.MovePlayerEvent;
import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.company.server.util.IdentifyablePayload;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



public class MoveEventResolverIncomingHandler extends SimpleEventIncomingHandler<IdentifyablePayload<MoveEvent>> {
    @Override
    public void process0(SocketIOClient client, IdentifyablePayload<MoveEvent> data, AckRequest ackRequest) {
        MoveEvent payload = data.getInnerPayload().getPayload();
        next(client, new MovePlayerEvent(data.getId(), payload.getX(), payload.getY(), payload.getRotation()), ackRequest);
    }
}
