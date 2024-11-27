package dev.duncan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.duncan.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

}
