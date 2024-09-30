package dev.simpleapp.twitter.security.services.impl;

import dev.simpleapp.twitter.security.model.UserRole;
import dev.simpleapp.twitter.security.repository.UserRoleRepository;
import dev.simpleapp.twitter.security.services.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserRole> findUserRole() {
        return userRoleRepository.findByAuthority("ROLE_USER");
    }
}
