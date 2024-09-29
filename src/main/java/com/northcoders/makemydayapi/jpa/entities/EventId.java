package com.northcoders.makemydayapi.jpa.entities;

// I use this class for a composite key as explained in here:
// https://www.baeldung.com/jpa-composite-primary-keys
// https://www.callicoder.com/hibernate-spring-boot-jpa-composite-primary-key-example/

import lombok.Data;

import java.io.Serializable;

@Data
public class EventId implements Serializable {
    private String id;
    private SourceEvent sourceEvent;
}
