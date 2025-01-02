package dev.simpleapp.twitter.user.profile.api.service;

import dev.simpleapp.twitter.user.profile.model.UserProfile;

import java.util.Optional;

public interface UserProfileApiService {
    UserProfile findUserProfileById(long userProfileId);

}
