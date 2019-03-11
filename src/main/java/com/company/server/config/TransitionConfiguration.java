package com.company.server.config;

import com.company.server.states.possibilities.InMemoryStateHolder;
import com.company.server.states.possibilities.StateHolder;
import com.company.server.states.transitions.BaseTransitionHandler;
import com.company.server.states.transitions.TransitionHandler;
import com.company.server.states.transitions.storage.JavaConfiguredTransitionMachine;
import com.company.server.states.transitions.storage.TransitionMachine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class TransitionConfiguration {

    @Bean
    public StateHolder stateHolder(){
        return new InMemoryStateHolder();
    }


    @Bean
    @Scope("prototype")
    public TransitionHandler transitionHandler(){
        return new BaseTransitionHandler();
    }



    @Bean
    TransitionMachine stateStorage(){
        return new JavaConfiguredTransitionMachine()
                    .from("NONE")
                        .to("AUTHORIZE")
                        .to("BACK_TO_MENU")
                    .and()
                    .from("AUTHORIZE_SUCCESS")
                        .to("MAIN_MENU")
                        .to("BACK_TO_MENU")
                    .and()
                    .from("MAIN_MENU")
                        .to("GAME")
                        .to("SETTINGS")
                        .to("BACK_TO_MENU")
                    .and()
                    .from("GAME")
                        .to("MOVE_TO_POINT")
                    .and()
                    .build();

    }
}
