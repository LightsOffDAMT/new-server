package com.company.server.states.transitions.storage;

public interface TransitionMachine {
    boolean canTransit(String from, String to);
}
