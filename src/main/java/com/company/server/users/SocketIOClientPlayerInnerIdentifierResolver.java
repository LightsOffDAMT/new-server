package com.company.server.users;

import com.company.server.states.possibilities.StateHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@Component
public class SocketIOClientPlayerInnerIdentifierResolver implements PlayerInnerIdentifierResolver {
    private final ConcurrentHashMap<String, String> resolveMap = new ConcurrentHashMap<>();

    @Override
    public String getInnerId(String outerId) {
        return resolveMap.get(outerId);
    }

    @Override
    public void putResolverPair(String outerId, String innerID) {
        resolveMap.put(outerId, innerID);
    }
}
