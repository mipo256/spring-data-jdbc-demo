package tech.mpolivaha.jdbc_demo.known.timestamps_disaster;

import org.springframework.data.repository.CrudRepository;

public interface OffsetDateTimeEntityRepository extends CrudRepository<OffsetDateTimeEntity, Long> {

}
