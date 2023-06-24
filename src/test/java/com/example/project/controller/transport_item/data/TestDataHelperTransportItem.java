package com.example.project.controller.transport_item.data;

import com.example.project.dto.CreateTransportItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class TestDataHelperTransportItem {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;


    public TestDataHelperTransportItem(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public ResultActions createTransportItemRequest(Long transportId, CreateTransportItem dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transports/" + transportId + "/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getAllTransportItemsRequest() throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/transports/1/items/all")
        );
    }

    public ResultActions getAllTransportItemsByTransportIdRequest(Long transportId) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/transports/" + transportId + "/items")
        );
    }

}
