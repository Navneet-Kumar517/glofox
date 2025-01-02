package com.glofox.glofox.service.interfaces;

import com.glofox.glofox.model.ClassBooking;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    ClassBooking bookClass(ClassBooking booking);
    List<ClassBooking> getBookingsForClass(String className, LocalDate date);
}

