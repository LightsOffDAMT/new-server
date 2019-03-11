package com.company.server.states.possibilities;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class InMemoryStateHolder implements StateHolder {
    private ConcurrentHashMap<String, String> stateHolder = new ConcurrentHashMap<>();

    @Override
    public String getState(Object identifier) {
        if(!(identifier instanceof String))
            return "ERROR";
        return Optional.ofNullable(
                stateHolder.get(identifier)
        ).orElse("NONE");
    }

    @Override
    public String setState(Object identifier, String state) {
        if(!(identifier instanceof String))
            return "ERROR";
        return stateHolder.put((String)identifier, state);
    }
}
