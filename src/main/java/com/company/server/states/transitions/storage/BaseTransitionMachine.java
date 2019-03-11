package com.company.server.states.transitions.storage;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;


public class BaseTransitionMachine implements TransitionMachine {
    protected ConcurrentHashMap<String, HashSet<String>> stateMap = new ConcurrentHashMap<>();

    @Override
    public boolean canTransit(String from, String to) {
        return stateMap.get(from).contains(to);
    }

    public void setStateMap(final ConcurrentHashMap<String, HashSet<String>> stateMap) {
        this.stateMap = stateMap;
    }
}
