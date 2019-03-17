package com.company.game.mechanics.handlers.move;

import com.company.game.mechanics.events.MovePlayerEvent;
import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.company.server.util.IdentifyablePayload;
import com.company.server.util.Payload;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;



public class MoveEventDeserializerIncomingHandler extends SimpleEventIncomingHandler<IdentifyablePayload<LinkedHashMap<String, Object>>> {
    private Logger LOGGER = LoggerFactory.getLogger(MoveEventDeserializerIncomingHandler.class);

    @Override
    public void process0(SocketIOClient client, IdentifyablePayload<LinkedHashMap<String, Object>> data, AckRequest ackRequest) {
        try{
            Payload<LinkedHashMap<String, Object>> innerPayload = data.getInnerPayload();

            LinkedHashMap<String, Object> innerMap = innerPayload.getPayload();
            String eventType = (String) innerMap.get("eventType");
            Double x = checkedCastToDouble(innerMap.get("x"));
            Double y = checkedCastToDouble(innerMap.get("y"));
            Double rotation = checkedCastToDouble(innerMap.get("rotation"));

            MovePlayerEvent movePlayerEvent = new MovePlayerEvent(data.getId(), x, y, rotation);
            movePlayerEvent.setEventType(eventType);

            Payload<MovePlayerEvent> payload = new Payload<>();
            payload.setEventName("MoveEvent");
            payload.setPayload(movePlayerEvent);

            IdentifyablePayload<MovePlayerEvent> result = new IdentifyablePayload<>();
            result.setId( data.getId() );
            result.setPayload(payload);

            next(client, result, ackRequest);
        } catch (ClassCastException e){
            LOGGER.error("Wrong format of MoveEvent from user with innerId: '{}'", client.getSessionId());
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    private Double checkedCastToDouble(Object target){
        Double res;
        try{
            res = (Double) target;
        } catch (ClassCastException e){
            res = ((Integer) target).doubleValue();
        }
        return res;
    }
}
