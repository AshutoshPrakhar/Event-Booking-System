package com.eventbooking.controller;

import com.eventbooking.entity.Booking;
import com.eventbooking.entity.User;
import com.eventbooking.repository.UserRepository;
import com.eventbooking.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService, UserRepository userRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{eventId}")
    public Booking bookTicket(@PathVariable Long eventId,
                              @RequestParam int qty){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();   // EMAIL FROM TOKEN

        return bookingService.bookTicket(eventId, qty, email);
    }
}
