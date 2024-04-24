package tech.mpolivaha.jdbc_demo.timestamps_disaster;

import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {

}
