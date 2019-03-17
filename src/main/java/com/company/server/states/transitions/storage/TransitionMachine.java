package com.company.server.states.transitions.storage;

import com.company.server.states.transitions.Transition;


public interface TransitionMachine {
    boolean canTransit(String from, String to);
}
