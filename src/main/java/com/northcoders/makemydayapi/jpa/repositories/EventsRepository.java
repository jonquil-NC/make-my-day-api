package com.northcoders.makemydayapi.jpa.repositories;

import com.northcoders.makemydayapi.jpa.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {
}
