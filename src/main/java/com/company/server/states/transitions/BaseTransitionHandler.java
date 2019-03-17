package com.company.server.states.transitions;

import com.company.game.entities.Player;
import com.company.game.mechanics.states.PlayerState;
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
    private TransitionMachine transitionMachine;
    private StateHolder stateHolder;

    @Override
    public Transition transit(String id, String to) {
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

        final String from = Optional.ofNullable(stateHolder.getState(id)).orElse(PlayerState.INITIAL_STATE);

        if(from.equals(PlayerState.INITIAL_STATE) )
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

    @Autowired
    public void setTransitionMachine(TransitionMachine transitionMachine) {
        this.transitionMachine = transitionMachine;
    }

    @Autowired
    public void setStateHolder(StateHolder stateHolder) {
        this.stateHolder = stateHolder;
    }
}
