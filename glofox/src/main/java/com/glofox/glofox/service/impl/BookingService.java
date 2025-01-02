package com.glofox.glofox.service.impl;

import com.glofox.glofox.constants.Constants;
import com.glofox.glofox.model.ClassBooking;
import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {
    //This map just stores the reference of studioClass not the actual objects, hence no duplication
    private Map<StudioClass, List<ClassBooking>> classToBookingsMap;
    private final ClassService classService;

    @Autowired
    public BookingService(ClassService classService) {
        this.classService = classService;
        classToBookingsMap = new HashMap<>();
    }

    public ClassBooking bookClass(ClassBooking booking) {
        // Find the class for the booking
        Optional<StudioClass> studioClass = classService.findClassByNameAndDate(
                booking.getClassName(),
                booking.getBookingDate()
        );

        if(studioClass.isEmpty()) {
            throw new IllegalArgumentException(Constants.NO_CLASS_FOUND);
        }

        // Add the class to the map if it doesn't exist
        classToBookingsMap.putIfAbsent(studioClass.get(), new ArrayList<>());

        //Check if booking already exists and throw exception if so
        boolean bookingExists = classToBookingsMap.get(studioClass.get()).stream()
                .anyMatch(b -> b.getMemberName().equals(booking.getMemberName()) &&
                        b.getClassName().equals(booking.getClassName()) &&
                        b.getBookingDate().equals(booking.getBookingDate()));

        if(bookingExists) {
            throw new IllegalArgumentException(Constants.ALREADY_BOOKED);
        }

        // Not implementing capacity check
        classToBookingsMap.get(studioClass.get()).add(booking);
        return booking;
    }

    public List<ClassBooking> getBookingsForClass(String className, LocalDate date) {
        return classToBookingsMap.get(classService.findClassByNameAndDate(className, date).orElseThrow(
                () -> new IllegalArgumentException(Constants.NO_CLASS_FOUND)
        ));
    }
}
