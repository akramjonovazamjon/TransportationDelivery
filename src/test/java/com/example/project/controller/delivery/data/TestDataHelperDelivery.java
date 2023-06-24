package com.example.project.controller.delivery.data;

import com.example.project.dto.CreateDelivery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class TestDataHelperDelivery {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public TestDataHelperDelivery(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public ResultActions createDeliveryRequest(CreateDelivery dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getDeliveryByIdRequest(Long id) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/deliveries/" + id)
        );
    }

}
