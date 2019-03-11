package com.company.server.eventHandlers.pipeline;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;


public interface EventIncomingHandler {
    void next(SocketIOClient client, Object data, AckRequest ackRequest);
    void process(SocketIOClient client, Object data, AckRequest ackRequest);
    void setNextHandler(EventIncomingHandler nextHandler);
}
