package com.example.project.controller.delivery;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateClientUser;
import com.example.project.dto.CreateDelivery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get deliveries ( GET /deliveries )")
public class GetDeliveryTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should get delivery status code 200")
    void shouldGetDeliveryById() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998977754546"));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        ResultActions resultActions = testDataHelperDelivery.getDeliveryByIdRequest(1L);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.owner.name").value("Axror"))
                .andExpect(jsonPath("$.result.owner.phoneNumber").value("+998977754546"));

    }

    @Test
    @DisplayName("should fail get delivery because of not found status code 200")
    void shouldFailGetDeliveryById() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998977754546"));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        ResultActions resultActions = testDataHelperDelivery.getDeliveryByIdRequest(2L);

        resultActions
                .andExpect(status().isConflict());
    }

}
