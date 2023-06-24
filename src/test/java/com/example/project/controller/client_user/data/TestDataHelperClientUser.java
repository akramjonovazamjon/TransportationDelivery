package com.example.project.controller.client_user.data;

import com.example.project.dto.CreateClientUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class TestDataHelperClientUser {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public TestDataHelperClientUser(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public ResultActions createClientUserRequest(CreateClientUser dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/client-users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getAllClientUserRequest() throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/client-users")
        );
    }

    public ResultActions getClientUserByIdRequest(Long id) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/client-users/" + id)
        );
    }


}
