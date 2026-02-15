package com.eventbooking.service;

import com.eventbooking.entity.Booking;
import com.eventbooking.entity.Event;
import com.eventbooking.entity.User;
import com.eventbooking.repository.BookingRepository;
import com.eventbooking.repository.EventRepository;
import com.eventbooking.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepo; // ADD

    // Browse events
    public List<Event> getAllEvents(){
        return eventRepo.findAll();
    }

    public Event getEvent(Long id){
        return eventRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // Organizer creates event
    public Event createEvent(Event event, String email){
        User organizer = userRepo.findByEmail(email).orElseThrow();
        event.setOrganizer(organizer);
        event.setAvailableTickets(event.getTotalTickets());
        return eventRepo.save(event);
    }
    @Transactional
    public Event updateEvent(Long eventId, Event updatedData){

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setTitle(updatedData.getTitle());
        event.setDescription(updatedData.getDescription());
        event.setLocation(updatedData.getLocation());
        event.setDateTime(updatedData.getDateTime());

        eventRepo.save(event);

        // background task 2
        List<Booking> bookings = bookingRepo.findByEventId(event.getId());

        List<User> users = bookings.stream()
                .map(Booking::getCustomer)
                .distinct()
                .toList();

        notificationService.notifyEventUpdate(event, users);

        return event;
    }

    // Organizer sees bookings
    public List<Booking> getEventBookings(Long eventId){
        return bookingRepo.findByEventId(eventId);
    }
}
