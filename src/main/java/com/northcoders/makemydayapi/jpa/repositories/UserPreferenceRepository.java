package com.northcoders.makemydayapi.jpa.repositories;

import com.northcoders.makemydayapi.jpa.entities.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
}
