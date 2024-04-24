package tech.mpolivaha.jdbc_demo.known.named_parameters;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

  //language=sql
  @Query(value = """
        
        SELECT *
        FROM PRODUCT
        WHERE CAST((ADDED_AT + INTERVAL ':amountOfDays days') AS DATE) < NOW()
        
  """)
  List<Product> findProductsAddedBefore(@Param("amountOfDays") Long amountOfDays);
}
