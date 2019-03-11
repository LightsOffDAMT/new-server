package com.company.server.broadcast;

import com.company.server.util.Payload;


public interface ResponseService {
    void submitBroadcastOperation(Payload payload);
    void submitSingleResponse(String destination, Payload payload);
}
