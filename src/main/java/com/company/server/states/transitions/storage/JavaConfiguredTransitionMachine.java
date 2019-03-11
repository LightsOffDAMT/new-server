package com.company.server.states.transitions.storage;

import java.util.HashSet;


public class JavaConfiguredTransitionMachine extends BaseTransitionMachine implements TransitionMachine {

    public class EntryConfigurer{
        private String from;
        private HashSet<String> values = new HashSet<>();

        public EntryConfigurer(final String from) {
            this.from = from;
        }

        public EntryConfigurer to(final String destination) {
            values.add(destination);
            return this;
        }

        public JavaConfiguredTransitionMachine and(){
            stateMap.put(from, values);
            return JavaConfiguredTransitionMachine.this;
        }

    }

    public JavaConfiguredTransitionMachine(){super();}

    public EntryConfigurer from(String source){
        return new EntryConfigurer(source);
    }

    public JavaConfiguredTransitionMachine build(){
        return this;
    }
}
