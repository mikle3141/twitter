package dev.simpleapp.twitter.security.services.impl;

import dev.simpleapp.twitter.security.mapper.UserAccountToUserMapper;
import dev.simpleapp.twitter.security.services.UserAccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountService userAccountService;
    private final UserAccountToUserMapper mapper;

    public UserDetailsServiceImpl(UserAccountService userAccountService, UserAccountToUserMapper mapper) {
        this.userAccountService = userAccountService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userAccountService
                .findUserByUsername(username)
                .map(this.mapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("Неверные учетные данные пользователя")); //"Bad credentials"
    }
}
