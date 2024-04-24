package tech.mpolivaha.jdbc_demo.known;


import java.time.OffsetDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.named_parameters.Product;
import tech.mpolivaha.jdbc_demo.known.named_parameters.ProductRepository;

@SpringBootTest(classes = Application.class)
public class ParametersInSingleQuotes extends AbstractIntegrationTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void givenCustomParameter_whenQueryingData_thenSeeWhatHappens() {
    productRepository.save(new Product().setAddedAt(OffsetDateTime.now().minusDays(2)));

    List<Product> productsAddedDayBeforeAndEarlier = productRepository.findProductsAddedBefore(1L);

    Assertions.assertThat(productsAddedDayBeforeAndEarlier).hasSize(1);
  }
}
