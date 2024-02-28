package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;
import org.springframework.data.jdbc.repository.config.DefaultQueryMappingConfiguration;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.ComplexEntityRowMapper;
import tech.mpolivaha.jdbc_demo.known.CustomRowMapperTest_8.CurrentConfig;
import tech.mpolivaha.jdbc_demo.known.quety_mapping_is_broken.ComplexEntity;
import tech.mpolivaha.jdbc_demo.known.quety_mapping_is_broken.ComplexEntityRepository;

@SpringBootTest(classes = Application.class)
@Import(CurrentConfig.class)
public class CustomRowMapperTest_8 extends AbstractIntegrationTest {

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
  void givenCustomRowMapperDefined_whenTryignToFoundEntity_thenSeeWhatHappens() {
    Optional<ComplexEntity> data = complexEntityRepository.findById(1L);
    assertThat(data).isPresent();
    assertThat(data.get().getProperties()).hasSize(2);
  }

  @Test
  void givenCustomRowMapperDefined_whenTryignToFoundEntityAsShould_thenItWorks() {
    Optional<ComplexEntity> data = complexEntityRepository.findByIdAsItShouldBe(1L);
    assertThat(data).isPresent();
    assertThat(data.get().getProperties()).hasSize(2);
  }
}
