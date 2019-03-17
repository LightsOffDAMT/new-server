package com.company.server.users;

public interface PlayerInnerIdentifierResolver {
    String getInnerId(String outerId);
    void putResolverPair(String outerId, String innerID);
}
