package com.company.server.eventHandlers;

import com.company.game.abilities.SimpleMovablePoint;
import com.company.server.dto.MoveEvent;
import com.company.server.dto.wrappers.EventWithPlayerWrapper;
import com.company.server.eventHandlers.pipeline.SimpleEventIncomingHandler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Component;

@Component
public class MovePlayerIncomingHandler extends SimpleEventIncomingHandler<EventWithPlayerWrapper> {

    @Override
    public void process0(SocketIOClient client, EventWithPlayerWrapper data, AckRequest ackRequest) {
        SimpleMovablePoint playerPosition = data.getPlayer().position;
        MoveEvent moveEvent = (MoveEvent) data.getPayload();

        switch (moveEvent.getMoveType()){
            case POINT:
                playerPosition.moveToPoint(moveEvent.getX(), moveEvent.getX());
                break;
            case DIFF:
                playerPosition.moveByDiff(moveEvent.getX(), moveEvent.getX());
                break;
            case SPEED:
                playerPosition.moveBySpeed(moveEvent.getX(), moveEvent.getX());
                break;

        }

    }
}
