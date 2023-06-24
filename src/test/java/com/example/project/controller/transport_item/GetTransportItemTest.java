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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get detailed delivery ( GET /transports/{transportId}/items )")
public class GetTransportItemTest extends CommonIntegrationTest {

    @Test
    @DisplayName("get detailed delivery status code 200")
    void shouldGetDetailedDelivery() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("01A475AA"));

        testDataHelperTransport.createTransportRequest(new CreateTransport("01A001AA"));

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900020506"));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        testDataHelperTransportItem.createTransportItemRequest(
                1L,
                new CreateTransportItem(
                        Timestamp.valueOf("2023-01-01 08:30:00"),
                        "Tashkent",
                        Timestamp.valueOf("2023-02-05 08:30:00"),
                        "London",
                        1L,
                        150000L,
                        List.of("Manchester")));

        testDataHelperTransportItem.createTransportItemRequest(
                2L,
                new CreateTransportItem(
                        Timestamp.valueOf("2023-01-01 08:30:00"),
                        "Fergana",
                        Timestamp.valueOf("2023-02-05 08:30:00"),
                        "Paris",
                        2L,
                        450000L,
                        List.of("Berlin")));

        ResultActions resultActions = testDataHelperTransportItem.getAllTransportItemsRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result.[0].id").value(1))
                .andExpect(jsonPath("$.result.[0].departureAddress").value("Tashkent"))
                .andExpect(jsonPath("$.result.[0].arrivalAddress").value("London"))
                .andExpect(jsonPath("$.result.[0].distance").value(150000L))
                .andExpect(jsonPath("$.result.[0].delivery.id").value(1))
                .andExpect(jsonPath("$.result.[0].delivery.owner.name").value("Axror"))
                .andExpect(jsonPath("$.result.[0].delivery.owner.phoneNumber").value("+998900020506"))
                .andExpect(jsonPath("$.result.[0].transport.number").value("01A475AA"))
                .andExpect(jsonPath("$.result.[0].stoppedAddresses", hasSize(1)))
                .andExpect(jsonPath("$.result.[0].stoppedAddresses.[0]").value("Manchester"))

                .andExpect(jsonPath("$.result.[1].id").value(2))
                .andExpect(jsonPath("$.result.[1].departureAddress").value("Fergana"))
                .andExpect(jsonPath("$.result.[1].arrivalAddress").value("Paris"))
                .andExpect(jsonPath("$.result.[1].distance").value(450000L))
                .andExpect(jsonPath("$.result.[1].delivery.id").value(2))
                .andExpect(jsonPath("$.result.[1].delivery.owner.name").value("Axror"))
                .andExpect(jsonPath("$.result.[1].delivery.owner.phoneNumber").value("+998900020506"))
                .andExpect(jsonPath("$.result.[1].transport.number").value("01A001AA"))
                .andExpect(jsonPath("$.result.[1].stoppedAddresses", hasSize(1)))
                .andExpect(jsonPath("$.result.[1].stoppedAddresses.[0]").value("Berlin"));

    }

    @Test
    @DisplayName("get detailed delivery by id status code 200")
    void shouldGetDetailedDeliveryById() throws Exception {

        testDataHelperTransport.createTransportRequest(new CreateTransport("01A475AA"));

        testDataHelperTransport.createTransportRequest(new CreateTransport("01A001AA"));

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900020506"));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        testDataHelperDelivery.createDeliveryRequest(new CreateDelivery(1L));

        testDataHelperTransportItem.createTransportItemRequest(
                1L,
                new CreateTransportItem(
                        Timestamp.valueOf("2023-01-01 08:30:00"),
                        "Tashkent",
                        Timestamp.valueOf("2023-02-05 08:30:00"),
                        "London",
                        1L,
                        150000L,
                        List.of("Manchester")));

        testDataHelperTransportItem.createTransportItemRequest(
                2L,
                new CreateTransportItem(
                        Timestamp.valueOf("2023-01-01 08:30:00"),
                        "Fergana",
                        Timestamp.valueOf("2023-02-05 08:30:00"),
                        "Paris",
                        2L,
                        450000L,
                        List.of("Moscov")));

        ResultActions resultActions = testDataHelperTransportItem.getAllTransportItemsByTransportIdRequest(2L);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(1)))
                .andExpect(jsonPath("$.result.[0].id").value(2))
                .andExpect(jsonPath("$.result.[0].departureAddress").value("Fergana"))
                .andExpect(jsonPath("$.result.[0].arrivalAddress").value("Paris"))
                .andExpect(jsonPath("$.result.[0].distance").value(450000L))
                .andExpect(jsonPath("$.result.[0].delivery.id").value(2))
                .andExpect(jsonPath("$.result.[0].delivery.owner.name").value("Axror"))
                .andExpect(jsonPath("$.result.[0].delivery.owner.phoneNumber").value("+998900020506"))
                .andExpect(jsonPath("$.result.[0].transport.number").value("01A001AA"))
                .andExpect(jsonPath("$.result[0].stoppedAddresses", hasSize(1)))
                .andExpect(jsonPath("$.result[0].stoppedAddresses.[0]").value("Moscov"));

    }

}
