package com.example.cinemaapp.Controllers;

import com.example.cinemaapp.Model.*;
import com.example.cinemaapp.Repository.*;
import com.example.cinemaapp.Service.MovieService;
import com.example.cinemaapp.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    @Autowired
    SeatRepo seatRepo;


    @Autowired
    MoviesRepo moviesRepo;

    @PostMapping("/seat/add")
    public ResponseEntity<Seat> addASeat(@RequestBody Seat seat) throws Exception{
        seat = seatService.addSeat(seat);
        return ResponseEntity.ok(seat);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Seat>> viewSeatList() throws Exception {
        return ResponseEntity.ok(seatService.viewSeatList());
    }
    @GetMapping("/findByDate/{date}")
    public ResponseEntity<List<Seat>> viewAllSeatsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) throws Exception {
        return ResponseEntity.ok(seatService.showAllSeats(date));
    }

    @GetMapping("/findByDate/{date}/{roomId}")
    public ResponseEntity<List<Seat>> viewAllSeatsByDateAndRoomId(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date, @RequestParam("roomId") Integer roomId) throws Exception {
        return ResponseEntity.ok(seatService.showAllSeatsByDateAndRoom(date,roomId));
    }

    @PutMapping("/update")
    public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat) throws Exception {
        ResponseEntity<Seat> response = null;
        if (seat == null) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            seat = seatService.updateSeat(seat);
            response = new ResponseEntity<>(seat, HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/bookSeat")
    public ResponseEntity<Seat> BookASeat(@RequestBody Seat seat)throws Exception {
        seat = seatService.bookSeat(seat);
        return ResponseEntity.ok(seat);
    }

    @PutMapping("/cancel")
    public ResponseEntity<Seat> CancelASeat(@RequestBody Seat seat) throws Exception {
        seat = seatService.cancelSeatBooking(seat);
        return ResponseEntity.ok(seat);
    }
}
