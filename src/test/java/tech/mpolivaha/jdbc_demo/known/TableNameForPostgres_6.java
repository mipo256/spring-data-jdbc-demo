package tech.mpolivaha.jdbc_demo.known;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.forcedquotation.Order;
import tech.mpolivaha.jdbc_demo.known.forcedquotation.OrderRepository;

@SpringBootTest(classes = Application.class)
public class TableNameForPostgres_6 extends AbstractIntegrationTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void whenSavingOrder_thenOrderShouldBeSaved() {
    Order entity = new Order().setName("Some name").setCount(7);
    orderRepository.save(entity);
  }
}
