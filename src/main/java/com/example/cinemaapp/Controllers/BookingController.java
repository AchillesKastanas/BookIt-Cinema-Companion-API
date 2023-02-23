package com.example.cinemaapp.Controllers;

import com.example.cinemaapp.Model.TicketsBooking;
import com.example.cinemaapp.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {
    @Autowired
    private BookingService service;

    @GetMapping("/bookings")
    public List<TicketsBooking> getAllBookings(){
        return service.showAllBooking();
    }

    @PostMapping("/booking")
    public TicketsBooking addBooking(@RequestBody TicketsBooking booking){
        return service.addBooking(booking);
    }

    @PutMapping("/booking")
    public TicketsBooking updateBooking(@RequestBody TicketsBooking booking){
        return service.updateBooking(booking);
    }

    @DeleteMapping("/booking")
    public TicketsBooking deleteBooking(@RequestBody TicketsBooking booking){
        return service.cancelBooking(booking);
    }
}