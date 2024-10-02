package dev.simpleapp.twitter.security.services;

import dev.simpleapp.twitter.security.model.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    void createUserAccount(UserAccount userAccount);

    Optional<UserAccount> findUserByUsername(String username);
}
