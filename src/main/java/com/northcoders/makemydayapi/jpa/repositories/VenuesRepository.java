package com.northcoders.makemydayapi.jpa.repositories;

import com.northcoders.makemydayapi.jpa.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenuesRepository extends JpaRepository<Venue, Long> {
}
