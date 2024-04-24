package tech.mpolivaha.jdbc_demo.query_mapping;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComplexEntityRepository extends CrudRepository<ComplexEntity, Long> {

  @Override
  Optional<ComplexEntity> findById(Long aLong);

  @Query(value = "SELECT * FROM COMPLEX_ENTITY WHERE ID = :id")
  Optional<ComplexEntity> findByIdUsingStringBasedQuery(Long id);
}
