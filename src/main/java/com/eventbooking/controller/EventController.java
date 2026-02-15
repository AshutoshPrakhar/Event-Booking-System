package com.eventbooking.controller;

import com.eventbooking.entity.Booking;
import com.eventbooking.entity.Event;
import com.eventbooking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // PUBLIC : browse events
    @GetMapping
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    // ORGANIZER : create event
    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return eventService.createEvent(event, email);
    }

    // ORGANIZER : update event
    @PutMapping("/update/{eventId}")
    public Event updateEvent(@PathVariable Long eventId,
                             @RequestBody Event event){
        return eventService.updateEvent(eventId, event);
    }

    // ORGANIZER : see bookings of event
    @GetMapping("/{eventId}/bookings")
    public List<Booking> eventBookings(@PathVariable Long eventId){
        return eventService.getEventBookings(eventId);
    }
}
