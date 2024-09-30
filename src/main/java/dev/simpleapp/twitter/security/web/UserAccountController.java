package dev.simpleapp.twitter.security.web;

import dev.simpleapp.twitter.security.model.UserAccount;
import dev.simpleapp.twitter.security.model.UserRole;
import dev.simpleapp.twitter.security.services.UserAccountService;
import dev.simpleapp.twitter.security.services.UserRoleService;
import dev.simpleapp.twitter.security.web.model.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public UserAccountController(UserAccountService userAccountService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registryAccount(@RequestBody RegisterRequest registerRequest) {
        log.info("Register request: {}", registerRequest);
        Assert.hasLength(registerRequest.username(), "User name should not be null or empty!");
        Assert.hasLength(registerRequest.password(), "User password should not be null or empty!");
        UserRole userRole = userRoleService
                .findUserRole()
                .orElseThrow(() -> new RuntimeException("User role not found!"));
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerRequest.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(passwordEncoder.encode(registerRequest.password()));
        userAccount.setAuthorities(Set.of(userRole));
        userAccountService.createUserAccount(userAccount);
    }

}
