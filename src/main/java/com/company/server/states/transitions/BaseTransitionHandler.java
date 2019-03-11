package com.company.server.states.transitions;

import com.company.server.states.TransitionRule;
import com.company.server.states.annotations.RandomString;
import com.company.server.states.possibilities.StateHolder;
import com.company.server.states.transitions.storage.TransitionMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class BaseTransitionHandler implements TransitionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseTransitionHandler.class);
    @RandomString(length = 5)
    private String id;
    private TransitionMachine transitionMachine;
    private StateHolder stateHolder;

    @Override
    public Transition transit(String to) {
        if(id == null){
            LOGGER.error("Unable to transit() of user with ID = null");
            throw new RuntimeException("Unable to transit() of user with ID = null");
        }

        if(to == null){
            LOGGER.error("Unable to transit() to null");
            throw new RuntimeException("Unable to transit() to null");
        }

        if(!to.chars().allMatch((value -> Character.isDigit(value) || Character.isAlphabetic(value)))){
            LOGGER.error("State name should consist only digits or alphabetic chars");
            throw new RuntimeException("State name should consist only digits or alphabetic chars");
        }

        final String from = Optional.ofNullable(stateHolder.getState(id)).orElse("NONE");

        if(from.equals("NONE"))
            stateHolder.setState(id, from);

        LOGGER.debug("Requested transition from \'{}\' to \'{}\', by user with ID:\'{}\'", from, to, id);
        if(transitionMachine.canTransit(from, to)){
            LOGGER.debug("Allowed transition from \'{}\' to \'{}\', by user with ID:\'{}\'", from, to, id);
            stateHolder.setState(id, to);
            return new Transition(TransitionRule.TRANSIT, to);
        }
        LOGGER.debug("Declined transition from \'{}\' to \'{}\', by user with ID:\'{}\'", from, to, id);
        return new Transition(TransitionRule.NONE, "Declined");
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Autowired
    public void setTransitionMachine(TransitionMachine transitionMachine) {
        this.transitionMachine = transitionMachine;
    }

    @Autowired
    public void setStateHolder(StateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }
}
