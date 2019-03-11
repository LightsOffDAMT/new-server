package com.company.server.states.transitions;

import com.company.server.states.TransitionRule;

import java.util.Optional;


public class Transition {
    private TransitionRule rule;
    private String destination;

    public Transition(final TransitionRule rule, final String destination) {
        this.rule = rule;
        this.destination = destination;
    }

    public TransitionRule getRule() {
        return rule;
    }

    public Transition setRule(TransitionRule rule) {
        this.rule = rule;
        return this;
    }

    public Optional<String> getDestination() {
        return Optional.of(destination);
    }

    public Transition setDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
