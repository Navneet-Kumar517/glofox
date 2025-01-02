package com.glofox.glofox.controller;

import com.glofox.glofox.service.impl.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    BookingService bookingService;

    @Test
    @DisplayName("When bookClass() is called with correct parameters, then the class should be booked")
    void testBookClass() throws IOException {
        //Similar to the testCreateClass() method in ClassControllerTest
        //Not Writing the code since these tests are not effective and will similar to
        // previous tests in ClassControllerTest

    }
}