package dev.simpleapp.twitter.user.profile.web;

import dev.simpleapp.twitter.user.profile.usecase.UserProfileRegisterUseCase;
import dev.simpleapp.twitter.user.profile.web.model.UserProfileRegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {
    private final UserProfileRegisterUseCase registerUseCase;

    public UserProfileController(UserProfileRegisterUseCase registerUseCase) {
        this.registerUseCase = registerUseCase;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserProfile(@Valid @RequestBody UserProfileRegisterRequest registerRequest) {
        registerUseCase.registerUserProfile(registerRequest);
    }
}
