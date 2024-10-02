package dev.simpleapp.twitter.security.repository;

import dev.simpleapp.twitter.security.model.UserAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUsername(String username);
    @EntityGraph(attributePaths = "authorities")
    Optional<UserDetails> findByUsername(String username);
}
