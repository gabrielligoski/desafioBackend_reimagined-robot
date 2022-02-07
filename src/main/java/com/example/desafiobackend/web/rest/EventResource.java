package com.example.desafiobackend.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.desafiobackend.model.Event;
import com.example.desafiobackend.model.User;
import com.example.desafiobackend.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EventResource {

    private final EventRepository eventRepository;

    public EventResource(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable Long id) {
        System.out.printf("REST request to get event : {%s}%n", id);
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        System.out.println("REST request to get All events");

        return ResponseEntity.status(HttpStatus.OK).body(eventRepository.findAll());
    }

    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) throws URISyntaxException {
        System.out.print("REST request to create event");

        Event newEvent = new Event();
        newEvent.setEventType(event.getEventType());
        newEvent.setEventDate(new Timestamp(new Date().getTime()));

        var result = eventRepository.save(newEvent);

        return ResponseEntity.created(new URI("/api/event/" + result.getId()))
                .body(result);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) throws URISyntaxException {
        System.out.print("REST request to update a event");
        var result = eventRepository.save(event);
        if (result.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        if (eventRepository.findById(event.getId()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        eventRepository.findById(event.getId()).map(e -> {
            e.setEventType(event.getEventType());
            return null;
        });
        return ResponseEntity.created(new URI("/api/event/" + result.getId()))
                .body(result);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        System.out.print("REST request to delete a event");
        eventRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}