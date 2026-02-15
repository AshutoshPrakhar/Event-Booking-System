package com.eventbooking.service;

import com.eventbooking.entity.Event;
import com.eventbooking.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Async
    public void sendBookingConfirmation(User user, Event event){
        System.out.println("EMAIL SENT TO " + user.getEmail() +
                " for event " + event.getTitle());
    }

    @Async
    public void notifyEventUpdate(Event event, List<User> users){
        users.forEach(u ->
                System.out.println("NOTIFY " + u.getEmail() +
                        " Event updated: " + event.getTitle())
        );
    }
}

