package dev.simpleapp.twitter.user.tweet.service;

import dev.simpleapp.twitter.user.profile.model.UserProfile;
import dev.simpleapp.twitter.user.tweet.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface TweetService {
    Tweet createTweet(Tweet tweet);
    Tweet updateTweet(Tweet updateTweet);
    Optional<Tweet> findTweetById(long tweetId);
    void deleteTweet(long tweetId);
    Page<Tweet> findAllTweets(UserProfile owner, Pageable pageable);
}
