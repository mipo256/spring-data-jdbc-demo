package tech.mpolivaha.jdbc_demo.customconversions;

import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}
