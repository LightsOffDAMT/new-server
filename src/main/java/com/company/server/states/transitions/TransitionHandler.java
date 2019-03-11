package com.company.server.states.transitions;

public interface TransitionHandler {
    Transition transit(String to);
    void setId(String id);
}
