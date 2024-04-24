package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.ConfigurationForSpringDataJdbcTests;
import tech.mpolivaha.jdbc_demo.known.idgeneration.Aircraft;
import tech.mpolivaha.jdbc_demo.known.idgeneration.AircraftRepository;
import tech.mpolivaha.jdbc_demo.known.idgeneration.ArticleRepository;

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

    assertThat(aircraft).isPresent().hasValueSatisfying(it -> it.getModel().equals("My Model"));
  }

  @Test
  void whenUsingBeforeSaveCallback_thenConversionHappensBeforeCallback() {
    assertThatThrownBy(() -> aircraftRepository.save(new Aircraft("My Model"))).doesNotThrowAnyException();
  }

  @Test
  void whenUsingCustomBeforeConvertCallback_thenIdAssignmentShouldNotWork() {
    Aircraft saved = aircraftRepository.save(new Aircraft().setModel("Spring Data Jdbc"));

    assertThat(aircraftRepository.findById(saved.getId()).map(Aircraft::getModel)).hasValue("Spring Data Jdbc");
  }
}