package com.example.project.controller.transport;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create transport ( POST /transports )")
public class CreateTransportTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should create transport status code 201")
    void shouldCreateTransport() throws Exception {

        ResultActions resultActions = testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.number").value("10A475AA"));

    }

    @Test
    @DisplayName("should fail create transport because of duplicate status code 409")
    void shouldFailCreateTransportByDuplicate() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        ResultActions resultActions = testDataHelperTransport.createTransportRequest(new CreateTransport("10A475AA"));

        resultActions
                .andExpect(status().isConflict());

    }

    @Test
    @DisplayName("should fail create transport because of required field null status code 409")
    void shouldFailCreateTransportByRequiredField() throws Exception {

        ResultActions resultActions = testDataHelperTransport.createTransportRequest(new CreateTransport(null));

        resultActions
                .andExpect(status().isBadRequest());

    }

}
