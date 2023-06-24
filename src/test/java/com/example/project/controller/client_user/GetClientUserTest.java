package com.example.project.controller.client_user;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateClientUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get client users ( GET /client-users )")
public class GetClientUserTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should get client users status code 200")
    void shouldGetClientUsers() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900010101"));

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Abror", "+998900010102"));

        ResultActions resultActions = testDataHelperClientUser.getAllClientUserRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result.[0].name").value("Axror"))
                .andExpect(jsonPath("$.result.[0].phoneNumber").value("+998900010101"))
                .andExpect(jsonPath("$.result.[1].name").value("Abror"))
                .andExpect(jsonPath("$.result.[1].phoneNumber").value("+998900010102"));
    }


    @Test
    @DisplayName("should get client user by id status code 200")
    void shouldGetClientUserById() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900010101"));

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Abror", "+998900010102"));

        ResultActions resultActions = testDataHelperClientUser.getClientUserByIdRequest(2L);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("Abror"))
                .andExpect(jsonPath("$.result.phoneNumber").value("+998900010102"));
    }


}
