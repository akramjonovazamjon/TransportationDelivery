package com.example.project.controller.delivery;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateClientUser;
import com.example.project.dto.CreateDelivery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create delivery ( POST /deliveries )")
public class CreateDeliveryTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create delivery status code 201")
    void shouldCreateDelivery() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Abror", "+998900223344"));

        ResultActions resultActions = testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.owner.id").value(1))
                .andExpect(jsonPath("$.result.owner.name").value("Abror"))
                .andExpect(jsonPath("$.result.owner.phoneNumber").value("+998900223344"));

    }


}
