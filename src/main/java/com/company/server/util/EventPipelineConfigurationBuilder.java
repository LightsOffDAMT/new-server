package com.company.server.util;

import com.company.server.events.pipeline.EventIncomingHandler;
import com.company.server.events.pipeline.SimpleEventIncomingHandler;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.ArrayList;


public class EventPipelineConfigurationBuilder {
    private ArrayList<EventIncomingHandler> handlers = new ArrayList<>();

    public <T> EventPipelineConfigurationBuilder next(EventIncomingHandler handler){
        handlers.add(handler);
        return this;
    }

    public DataListener build(){
        //Checks and logging here
        if(handlers.isEmpty())
            throw new RuntimeException("Handler can't be empty");

        EventIncomingHandler bufferLink = null;
        EventIncomingHandler resultHandler = handlers.get(0);
        for (EventIncomingHandler handler : handlers) {
            if(bufferLink == null){
                bufferLink = handler;
                continue;
            }
            bufferLink.setNextHandler(handler);
            bufferLink = handler;
        }
        return resultHandler::process;
    }


}
