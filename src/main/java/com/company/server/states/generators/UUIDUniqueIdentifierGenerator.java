package com.company.server.states.generators;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUniqueIdentifierGenerator implements UniqueIdentifierGenerator {
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
