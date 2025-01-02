package com.glofox.glofox.controller;

import com.glofox.glofox.constants.Constants;
import com.glofox.glofox.model.ClassBooking;
import com.glofox.glofox.model.ResponseModel;
import com.glofox.glofox.service.impl.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<ResponseModel> bookClass(@Valid @RequestBody ClassBooking booking) {
        ClassBooking bookedClass = bookingService.bookClass(booking);
        return ResponseEntity.ok(new ResponseModel(Constants.CLASS_BOOKED_SUCCESSFULLY, bookedClass));
    }

    @GetMapping("/{className}/{date}")
    public ResponseEntity<List<ClassBooking>> getBookingsForClass(@PathVariable String className, @PathVariable LocalDate date) {
        return ResponseEntity.ok(bookingService.getBookingsForClass(className, date));
    }
}
