package com.company.server.states.possibilities;

public interface StateHolder {
    String getState(Object identifier);
    String setState(Object identifier, String state);
}
