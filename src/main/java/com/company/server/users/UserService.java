package com.company.server.users;

import java.util.Optional;


/**
 * Be aware
 * {@link UserService} should itself take a care about disconnected users
 * @param <USER_TYPE>
 */
public interface UserService<USER_TYPE> {
    Optional<USER_TYPE> getUser(String id);
    void addUser(String id, USER_TYPE user);
}
