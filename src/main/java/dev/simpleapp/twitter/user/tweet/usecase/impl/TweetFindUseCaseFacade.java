package dev.simpleapp.twitter.user.tweet.usecase.impl;

import dev.simpleapp.twitter.user.profile.api.service.CurrentUserProfileApiService;
import dev.simpleapp.twitter.user.profile.model.UserProfile;
import dev.simpleapp.twitter.user.tweet.mapper.TweetToTweetResponseMapper;
import dev.simpleapp.twitter.user.tweet.model.Tweet;
import dev.simpleapp.twitter.user.tweet.service.TweetService;
import dev.simpleapp.twitter.user.tweet.usecase.TweetFindUseCase;
import dev.simpleapp.twitter.user.tweet.web.model.TweetFindRequest;
import dev.simpleapp.twitter.user.tweet.web.model.TweetResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static dev.simpleapp.twitter.user.tweet.model.Tweet_.CREATE_TIMESTAMP;

@Component
public class TweetFindUseCaseFacade implements TweetFindUseCase {

    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final TweetService tweetService;
    private final TweetToTweetResponseMapper tweetToTweetResponseMapper;

    public TweetFindUseCaseFacade(CurrentUserProfileApiService currentUserProfileApiService,
                                  TweetService tweetService,
                                  TweetToTweetResponseMapper tweetToTweetResponseMapper) {
        this.currentUserProfileApiService = currentUserProfileApiService;
        this.tweetService = tweetService;
        this.tweetToTweetResponseMapper = tweetToTweetResponseMapper;
    }

    @Override
    public Collection<TweetResponse> findTweets(TweetFindRequest findRequest) {
        UserProfile owner = currentUserProfileApiService.currentUserProfile();

        Sort sort = Sort.by(Sort.Direction.DESC, CREATE_TIMESTAMP);

        Pageable pageable = PageRequest.of(findRequest.page(), findRequest.limit(), sort);

        Collection<Tweet> allOwnerTweets = tweetService.findAllTweets(owner, pageable);
        return allOwnerTweets
                .stream()
                .map(tweetToTweetResponseMapper::map)
                .toList();
    }
}
