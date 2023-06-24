package com.example.project.controller.transport.data;

import com.example.project.dto.CreateTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class TestDataHelperTransport {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public TestDataHelperTransport(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public ResultActions createTransportRequest(CreateTransport dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getAllTransportsRequest() throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/transports")
        );
    }

    public ResultActions getTransportByIdRequest(Long id) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/transports/" + id)
        );
    }
}
