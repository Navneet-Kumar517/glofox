package com.glofox.glofox.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.glofox.glofox.model.StudioClass;
import com.glofox.glofox.service.impl.ClassService;
import com.glofox.glofox.testUtils.DataUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassController.class)
class ClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ClassService classService;


    @Test
    @DisplayName("When createClass() is called with correct parameters, then the class should be created")
    void testCreateClass() throws Exception {
        // Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getCorrectClassResponse();
        JsonNode responseModel = DataUtil.getObjectFromString(response, JsonNode.class);
        StudioClass classRequest = DataUtil.getObjectFromString(request, StudioClass.class);

        when(classService.createClass(classRequest)).thenReturn
                (DataUtil.getObjectFromString(responseModel.get("data").toString(), StudioClass.class));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
        // Assert
        verify(classService, times(1)).createClass(classRequest);
        //We can add argument captors as well but this seems thorough enough
    }


    @Test
    @DisplayName("When createClass() is called with correct parameters, then the class should not be created")
    void testCreateClass_withTheSameRequestAgain_throwsBadRequest() throws Exception {
        // Arrange
        String request = DataUtil.getCorrectClassRequest();
        String response = DataUtil.getBadRequestClassResponse();
        StudioClass classRequest = DataUtil.getObjectFromString(request, StudioClass.class);

        when(classService.createClass(classRequest)).thenThrow(
                new IllegalArgumentException("A class with these details already exists"));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(content().json(response))
                .andExpect(status().is4xxClientError());
        // Assert
        verify(classService, times(1)).createClass(classRequest);
        //We can add argument captors as well but this seems thorough enough
    }
}