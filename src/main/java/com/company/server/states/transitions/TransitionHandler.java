package com.company.server.states.transitions;

import com.company.server.states.TransitionRule;
import org.apache.commons.lang3.tuple.Pair;


public interface TransitionHandler {
    Transition transit(String id, String to);
}
