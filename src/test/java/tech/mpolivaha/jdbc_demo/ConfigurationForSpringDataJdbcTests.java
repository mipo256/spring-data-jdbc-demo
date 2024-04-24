package tech.mpolivaha.jdbc_demo;

import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import tech.mpolivaha.jdbc_demo.idgeneration.Aircraft;

@Configuration
public class ConfigurationForSpringDataJdbcTests extends AbstractJdbcConfiguration {

  @Bean
  BeforeSaveCallback<Aircraft> beforeSaveCallback() {
    return (entity, s) -> {
      if (entity.getId() == null) {
        entity.setId(UUID.randomUUID());
      }
      return entity;
    };
  }

//  @Bean
  BeforeConvertCallback<Aircraft> beforeConvertCallback() {
    return (entity) -> {
      if (entity.getId() == null) {
        entity.setId(UUID.randomUUID());
      }
      return entity;
    };
  }
}
