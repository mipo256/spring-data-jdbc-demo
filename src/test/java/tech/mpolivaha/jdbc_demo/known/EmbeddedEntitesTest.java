package tech.mpolivaha.jdbc_demo.known;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.embedded.Address;
import tech.mpolivaha.jdbc_demo.known.embedded.CourseStudent;

@SpringBootTest(classes = Application.class)
public class EmbeddedEntitesTest extends AbstractIntegrationTest {

  @Autowired
  JdbcAggregateTemplate jdbcAggregateTemplate;

  @Test
  void givenEmbeddedEntityWithPrefix_whenSavingRoot_thenSeeWhatHappens() {
    CourseStudent instance = new CourseStudent(1L, "Alex", 4.2, new Address("Oslo", "Norway"));

    jdbcAggregateTemplate.insert(instance);

    CourseStudent found = jdbcAggregateTemplate.findById(1L, CourseStudent.class);

    Assertions.assertThat(found).usingRecursiveComparison().isEqualTo(instance);
  }
}
