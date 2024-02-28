package tech.mpolivaha.jdbc_demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;


public class AbstractIntegrationTest {

  protected static PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"));

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.username", () -> container.getUsername());
    registry.add("spring.datasource.password", () -> container.getPassword());
    registry.add("spring.datasource.url", () -> container.getJdbcUrl());
  }

  @BeforeAll
  static void beforeAll() {
    container.start();
  }

  @AfterAll
  static void afterAll() {
    container.stop();
  }
}
