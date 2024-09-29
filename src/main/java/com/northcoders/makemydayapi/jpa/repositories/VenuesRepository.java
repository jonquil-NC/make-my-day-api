package com.northcoders.makemydayapi.jpa.repositories;

import com.northcoders.makemydayapi.jpa.entities.Venue;
import com.northcoders.makemydayapi.jpa.entities.VenueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenuesRepository extends JpaRepository<Venue, VenueId> {
}
