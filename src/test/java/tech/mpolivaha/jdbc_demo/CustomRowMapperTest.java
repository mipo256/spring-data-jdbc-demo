package tech.mpolivaha.jdbc_demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import tech.mpolivaha.jdbc_demo.query_mapping.ComplexEntityRowMapper;
import tech.mpolivaha.jdbc_demo.CustomRowMapperTest.CurrentConfig;
import tech.mpolivaha.jdbc_demo.query_mapping.ComplexEntity;
import tech.mpolivaha.jdbc_demo.query_mapping.ComplexEntityRepository;

@Slf4j
@SpringBootTest(classes = Application.class)
@Import(CurrentConfig.class)
public class CustomRowMapperTest extends AbstractIntegrationTest {

  @Configuration
  static class CurrentConfig {
    @Bean
    QueryMappingConfiguration queryMappingConfiguration(ComplexEntityRowMapper complexEntityRowMapper) {
      return new DefaultQueryMappingConfiguration()
          .registerRowMapper(ComplexEntity.class, complexEntityRowMapper);
    }
  }

  @Autowired
  private ComplexEntityRepository complexEntityRepository;

  @Test
  void givenCustomRowMapperDefined_whenTryingToFoundEntity_thenSeeWhatHappens() {
    Optional<ComplexEntity> data = complexEntityRepository.findById(1L);
    log.info("Received data : {}", data);
    assertThat(data).isPresent();
    assertThat(data.get().getProperties()).hasSize(2);
  }

  @Test
  void givenCustomRowMapperDefined_whenTryingToFoundEntityAsShould_thenItWorks() {
    Optional<ComplexEntity> data = complexEntityRepository.findByIdUsingStringBasedQuery(1L);
    log.info("Received data : {}", data);
    assertThat(data).isPresent();
    assertThat(data.get().getProperties()).hasSize(2);
  }
}
