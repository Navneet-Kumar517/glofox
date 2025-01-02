package com.glofox.glofox.service;

import com.glofox.glofox.model.ClassBooking;
import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.impl.BookingService;
import com.glofox.glofox.service.impl.ClassService;
import com.glofox.glofox.testUtils.DataUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClassService classService;

    @BeforeAll
    void setUp() throws IOException {
        String request = DataUtil.getCorrectClassRequest();
        StudioClass studioClass = DataUtil.getObjectFromString(request, StudioClass.class);
        classService.createClass(studioClass);
    }

    @Test
    @Order(1)
    @DisplayName("Correct booking will be created when valid request is provided")
    void testBookClass() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectBookingRequest();
        ClassBooking booking = DataUtil.getObjectFromString(request, ClassBooking.class);
        //Act
        ClassBooking createdBooking = bookingService.bookClass(booking);
        //Assert
        assertEquals(booking, createdBooking);
    }

    @Test
    @Order(2)
    @DisplayName("Exception will be thrown when booking with same name, class name and date already exists")
    void testBookClass_throwsException() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectBookingRequest();
        ClassBooking booking = DataUtil.getObjectFromString(request, ClassBooking.class);
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bookingService.bookClass(booking));
    }

    @Test
    @Order(3)
    @DisplayName("Exception will be thrown when class not found")
    void testBookClass_throwsExceptionIfClassDoesNotExist() throws IOException {
        //Arrange
        String request = DataUtil.getWrongBookingRequest();
        ClassBooking booking = DataUtil.getObjectFromString(request, ClassBooking.class);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bookingService.bookClass(booking));
    }
}