package com.company.server.users;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketIOClientPlayerIdentifierResolver {
    /**
     * SocketIOClient sessionId <-> PlayerId
     */
    private ConcurrentHashMap<String, String> clients = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> storage() {
        return clients;
    }
}
