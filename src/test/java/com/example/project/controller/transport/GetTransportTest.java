package com.example.project.controller.transport;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get transport ( GET /transports )")
public class GetTransportTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should get transports status code 200")
    void shouldGetTransports() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A001AA"));

        ResultActions resultActions = testDataHelperTransport.getAllTransportsRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result.[0].number").value("10A475AA"))
                .andExpect(jsonPath("$.result.[1].number").value("10A001AA"));

    }

    @Test
    @DisplayName("should get transports by id status code 200")
    void shouldGetTransportById() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A001AA"));

        ResultActions resultActions = testDataHelperTransport.getTransportByIdRequest(2L);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(2))
                .andExpect(jsonPath("$.result.number").value("10A001AA"));

    }

    @Test
    @DisplayName("should fail get transports by id status code 200")
    void shouldFailGetTransportById() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A001AA"));

        ResultActions resultActions = testDataHelperTransport.getTransportByIdRequest(3L);

        resultActions
                .andExpect(status().isConflict());
    }

}
