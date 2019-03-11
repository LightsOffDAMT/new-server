package com.company.server.eventHandlers.pipeline;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class SimpleEventIncomingHandler<T> implements EventIncomingHandler {
    private final Logger LOGGER;
    private EventIncomingHandler nextHandler;

    public SimpleEventIncomingHandler(){
        LOGGER = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void next(SocketIOClient client, Object data, AckRequest ackRequest) {
        nextHandler.process(client, data, ackRequest);
    }

    @Override
    public void process(SocketIOClient client, Object data, AckRequest ackRequest){
        try{
            T msg = (T)data;
            process0(client, msg, ackRequest);
        } catch (ClassCastException e){
            LOGGER.error("Unable to continue event pipeline from client with contextID: \'{}\'", e);
        }
    }

    @Override
    public void setNextHandler(EventIncomingHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void process0(SocketIOClient client, T data, AckRequest ackRequest);
}
