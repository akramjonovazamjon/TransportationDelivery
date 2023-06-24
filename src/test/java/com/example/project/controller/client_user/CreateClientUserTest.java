package com.example.project.controller.client_user;

import com.example.project.controller.CommonIntegrationTest;
import com.example.project.dto.CreateClientUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create client user ( POST /client-users )")
public class CreateClientUserTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create client user status code 201")
    void shouldCreateClientUser() throws Exception {

        ResultActions resultActions = testDataHelperClientUser
                .createClientUserRequest(new CreateClientUser("Axror", "+998900010101"));

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Axror"))
                .andExpect(jsonPath("$.result.phoneNumber").value("+998900010101"));
    }

    @Test
    @DisplayName("should fail create client user because of duplicate status code 409")
    void shouldFailCreateClientUserByDuplicate() throws Exception {

        testDataHelperClientUser.createClientUserRequest(new CreateClientUser("Axror", "+998900010101"));

        ResultActions resultActions = testDataHelperClientUser
                .createClientUserRequest(new CreateClientUser("Axror", "+998900010101"));

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail create client user because of required fields null status code 400")
    void shouldFailCreateClientUserByRequiredFields() throws Exception {

        ResultActions resultActions = testDataHelperClientUser
                .createClientUserRequest(new CreateClientUser("Axror", null));

        resultActions.andExpect(status().isBadRequest());
    }


}
