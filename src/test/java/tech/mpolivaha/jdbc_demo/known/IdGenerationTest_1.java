package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.ConfigurationForSpringDataJdbcTests;
import tech.mpolivaha.jdbc_demo.known.idgeneration.Aircraft;
import tech.mpolivaha.jdbc_demo.known.idgeneration.AircraftRepository;
import tech.mpolivaha.jdbc_demo.known.idgeneration.Article;
import tech.mpolivaha.jdbc_demo.known.idgeneration.ArticleRepository;

@SpringBootTest(classes = Application.class)
@Import(ConfigurationForSpringDataJdbcTests.class)
class IdGenerationTest_1 extends AbstractIntegrationTest {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private JdbcAggregateTemplate jdbcAggregateTemplate;

  @Autowired
  private AircraftRepository aircraftRepository;

  @Test
  void whenUsingJdbcAggregateTemplate_thenInsertCanHelp() {
    UUID id = UUID.fromString("83d72eb5-9eb6-4218-959c-ce333b1fc5f2");

    assertThatThrownBy(() -> aircraftRepository.save(new Aircraft(id, "My Model")))
        .hasCauseInstanceOf(IncorrectUpdateSemanticsDataAccessException.class)
        .hasMessage("Failed to execute DbAction.UpdateRoot(entity=Aircraft(id=83d72eb5-9eb6-4218-959c-ce333b1fc5f2, model=My Model))");
  }

  @Test
  void whenUsingRepository_thenIdIsOverriddenAlways() {
    UUID id = UUID.randomUUID();

    jdbcAggregateTemplate.insert(new Aircraft(id, "My Model"));

    Optional<Aircraft> aircraft = aircraftRepository.findById(id);

    assertThat(aircraft).isPresent().hasValueSatisfying(it -> it.getModel().equals("My Model"));
  }

  @Test
  void whenUsingCustomBeforeConvertCallback_thenIdAssignmentDoesNotWork() {
    assertThatThrownBy(() -> articleRepository.save(new Article().setName("Spring Data Jdbc")))
        .isInstanceOf(DbActionExecutionException.class)
        .hasMessageContaining("Failed to execute InsertRoot");
  }
}