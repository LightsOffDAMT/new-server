package com.company.game.mechanics.handlers.util;

import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.company.server.states.TransitionRule;
import com.company.server.states.transitions.TransitionHandler;
import com.company.server.util.IdentifyablePayload;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class AvailabilityCheckEventIncomingHandler extends SimpleEventIncomingHandler<IdentifyablePayload> {
    private Logger LOGGER = LoggerFactory.getLogger(AvailabilityCheckEventIncomingHandler.class);
    private TransitionHandler transitionHandler;

    @Override
    public void process0(SocketIOClient client, IdentifyablePayload data, AckRequest ackRequest) {
        if(data.getId() == null ||
           transitionHandler.transit(data.getId(), data.getInnerPayload().getEventName()).getRule().equals(TransitionRule.NONE)){
            LOGGER.info("Event '{}' from user with innerId: '{}' declined", data.getInnerPayload().getEventName(), data.getId());
            return;
        }
        next(client, data, ackRequest);
    }

    @Autowired
    public AvailabilityCheckEventIncomingHandler setTransitionHandler(TransitionHandler transitionHandler) {
        this.transitionHandler = transitionHandler;
        return this;
    }
}
