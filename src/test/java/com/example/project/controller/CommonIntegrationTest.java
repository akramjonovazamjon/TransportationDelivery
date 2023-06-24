package com.example.project.controller;

import com.example.project.controller.client_user.data.TestDataHelperClientUser;
import com.example.project.controller.delivery.data.TestDataHelperDelivery;
import com.example.project.controller.transport.data.TestDataHelperTransport;
import com.example.project.controller.transport_item.data.TestDataHelperTransportItem;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class CommonIntegrationTest {

    @Autowired
    public TestDataHelperClientUser testDataHelperClientUser;

    @Autowired
    public TestDataHelperTransport testDataHelperTransport;

    @Autowired
    public TestDataHelperDelivery testDataHelperDelivery;

    @Autowired
    public TestDataHelperTransportItem testDataHelperTransportItem;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;


    static {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
                .withEnv("MAX_HEAP_SIZE", "256M")
                .withEnv("HEAP_NEWSIZE", "128M")
                .withReuse(true);

        Startables.deepStart(postgreSQLContainer).join();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void execute() {
        jdbcTemplate.execute("TRUNCATE TABLE client_users RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE deliveries RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE transport_items RESTART IDENTITY CASCADE ");
        jdbcTemplate.execute("TRUNCATE TABLE transports RESTART IDENTITY CASCADE ");
    }
}
