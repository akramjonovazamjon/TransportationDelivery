package com.example.project.controller.transport_item;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateClientUser;
import com.example.project.dto.CreateDelivery;
import com.example.project.dto.CreateTransport;
import com.example.project.dto.CreateTransportItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create transport item ( POST /transports/{transportId}/items )")
public class CreateTransportItemTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create transport item statsu code 201")
    void shouldCreateTransportItem() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("01A475AA"));

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900020506"));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        ResultActions resultActions = testDataHelperTransportItem.createTransportItemRequest(1L,
                new CreateTransportItem(
                        Timestamp.valueOf("2023-01-01 08:30:00"),
                        "Tashkent",
                        Timestamp.valueOf("2023-02-05 08:30:00"),
                        "London",
                        1L,
                        150000L));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.departureAddress").value("Tashkent"))
                .andExpect(jsonPath("$.result.arrivalAddress").value("London"))
                .andExpect(jsonPath("$.result.distance").value(150000))
                .andExpect(jsonPath("$.result.delivery.id").value(1))
                .andExpect(jsonPath("$.result.delivery.owner.id").value(1))
                .andExpect(jsonPath("$.result.delivery.owner.name").value("Axror"))
                .andExpect(jsonPath("$.result.delivery.owner.phoneNumber").value("+998900020506"))
                .andExpect(jsonPath("$.result.transport.id").value(1))
                .andExpect(jsonPath("$.result.transport.number").value("01A475AA"));
    }


}
