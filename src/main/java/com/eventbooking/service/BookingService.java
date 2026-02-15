package com.eventbooking.service;

import com.eventbooking.entity.Booking;
import com.eventbooking.entity.Event;
import com.eventbooking.entity.User;
import com.eventbooking.repository.BookingRepository;
import com.eventbooking.repository.EventRepository;
import com.eventbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public Booking bookTicket(Long eventId, int qty, String email){

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if(event.getAvailableTickets() < qty)
            throw new RuntimeException("Tickets not available");

        event.setAvailableTickets(event.getAvailableTickets() - qty);

        Booking booking = new Booking();
        booking.setCustomer(user);
        booking.setEvent(event);
        booking.setQuantity(qty);
        booking.setBookingTime(LocalDateTime.now());

        bookingRepo.save(booking);

        notificationService.sendBookingConfirmation(user,event);

        return booking;
    }


    // Required by assignment
    public List<Booking> getMyBookings(String email){
        User user = userRepo.findByEmail(email).orElseThrow();
        return bookingRepo.findByCustomerId(user.getId());
    }
}
