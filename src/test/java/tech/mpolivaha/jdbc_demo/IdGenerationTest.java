package tech.mpolivaha.jdbc_demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import tech.mpolivaha.jdbc_demo.idgeneration.Aircraft;
import tech.mpolivaha.jdbc_demo.idgeneration.AircraftRepository;
import tech.mpolivaha.jdbc_demo.idgeneration.ArticleRepository;

@SpringBootTest(classes = Application.class)
@Import(ConfigurationForSpringDataJdbcTests.class)
class IdGenerationTest extends AbstractIntegrationTest {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private JdbcAggregateTemplate jdbcAggregateTemplate;

  @Autowired
  private AircraftRepository aircraftRepository;

  @Test
  void whenUsingJdbcAggregateTemplate_thenInsertCanHelp() {
    UUID id = UUID.randomUUID();

    jdbcAggregateTemplate.insert(new Aircraft(id, "My Model"));

    Optional<Aircraft> aircraft = aircraftRepository.findById(id);

    assertThat(aircraft).isPresent().hasValueSatisfying(it -> assertThat(it.getModel()).isEqualTo("My Model"));
  }

  @Test
  void whenUsingCustomBeforeConvertCallback_thenIdAssignmentShouldWork() {
    Aircraft saved = aircraftRepository.save(new Aircraft().setModel("Spring Data Jdbc"));

    assertThat(aircraftRepository.findById(saved.getId()).map(Aircraft::getModel)).hasValue("Spring Data Jdbc");
  }
}