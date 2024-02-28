package tech.mpolivaha.jdbc_demo.known.quety_mapping_is_broken;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComplexEntityRepository extends CrudRepository<ComplexEntity, Long> {

  @Query(value = "SELECT * FROM COMPLEX_ENTITY WHERE ID = :id")
  Optional<ComplexEntity> findByIdAsItShouldBe(Long id);


//  @Override
//  @Query(rowMapperRef = "complexEntityRowMapper")
//  Optional<ComplexEntity> findById(Long id);
}
