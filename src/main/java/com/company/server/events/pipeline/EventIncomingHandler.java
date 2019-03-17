package com.company.server.events.pipeline;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;


public interface EventIncomingHandler {
    <R> void next(SocketIOClient client, R data, AckRequest ackRequest);
    void process(SocketIOClient client, Object data, AckRequest ackRequest);
    void setNextHandler(EventIncomingHandler nextHandler);
}
