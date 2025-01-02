package com.glofox.glofox.service;

import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.impl.ClassService;
import com.glofox.glofox.testUtils.DataUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ClassServiceTest {

    @Autowired
    private ClassService classService;

    @Test
    @Order(1)
    @DisplayName("Correct class will be created when valid request is provided")
    void testCreateClass() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectClassRequest();
        StudioClass studioClass = DataUtil.getObjectFromString(request, StudioClass.class);
        //Act
        StudioClass createdClass = classService.createClass(studioClass);
        //Assert
        assertEquals(studioClass, createdClass);
    }

    @Test
    @Order(2)
    @DisplayName("Exception will be thrown when class with same name and dates already exists")
    void testCreateClass_throwsException() throws IOException {
        //Arrange
        String request = DataUtil.getCorrectClassRequest();
        StudioClass studioClass = DataUtil.getObjectFromString(request, StudioClass.class);
        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> classService.createClass(studioClass));
    }
}