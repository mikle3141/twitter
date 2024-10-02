package dev.simpleapp.twitter.security.services.impl;

import dev.simpleapp.twitter.security.model.UserAccount;
import dev.simpleapp.twitter.security.repository.UserAccountRepository;
import dev.simpleapp.twitter.security.services.UserAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createUserAccount(UserAccount userAccount) {
        boolean isUsernameExists = userAccountRepository.existsByUsername(userAccount.getUsername());
        if (isUsernameExists) {
            throw new RuntimeException("Account with this username already exists");
        }
        userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserDetails> findUserByUsername(String username) {
        return this.userAccountRepository.findByUsername(username);
    }
}
