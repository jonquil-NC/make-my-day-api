package com.northcoders.makemydayapi.jpa.repositories;

import com.northcoders.makemydayapi.jpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
