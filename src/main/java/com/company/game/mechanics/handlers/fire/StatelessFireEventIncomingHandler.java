package com.company.game.mechanics.handlers.fire;

import com.company.game.abilities.SimpleMovablePoint;
import com.company.game.contributed.GameStorage;
import com.company.game.entities.Player;
import com.company.game.mechanics.events.FireEvent;
import com.company.server.broadcast.ResponseService;
import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.company.server.util.IdentifyablePayload;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


public class StatelessFireEventIncomingHandler extends SimpleEventIncomingHandler<IdentifyablePayload> {
    private ResponseService responseService;
    private GameStorage<ConcurrentHashMap<String, Player>> players;

    @Override
    public void process0(SocketIOClient client, IdentifyablePayload data, AckRequest ackRequest) {
        String id = data.getId();
        SimpleMovablePoint position = players.storage().get(id).position;

        FireEvent fireEvent = new FireEvent();
        fireEvent.setId(id)
                 .setPosition(position);

        Payload<FireEvent> payload = new Payload<>();
        payload.setEventName("FIRE_EVENT");
        payload.setPayload(fireEvent);

        responseService.submitBroadcastOperation(payload);
    }

    @Autowired
    public StatelessFireEventIncomingHandler setResponseService(ResponseService responseService) {
        this.responseService = responseService;
        return this;
    }

    @Autowired
    public StatelessFireEventIncomingHandler setPlayers(GameStorage<ConcurrentHashMap<String, Player>> players) {
        this.players = players;
        return this;
    }
}
