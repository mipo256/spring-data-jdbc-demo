package tech.mpolivaha.jdbc_demo;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import tech.mpolivaha.jdbc_demo.known.idgeneration.Aircraft;
import tech.mpolivaha.jdbc_demo.known.idgeneration.Article;

@Configuration
@Import(ComplexEntityRowMapper.class)
public class ConfigurationForSpringDataJdbcTests extends AbstractJdbcConfiguration {

  @Bean
  BeforeConvertCallback<Article> beforeConvertCallback() {
    return (entity) -> {
      if (entity.getId() == null) {
        entity.setId(UUID.randomUUID());
      }
      return entity;
    };
  }

  @Bean
  BeforeSaveCallback<Aircraft> beforeSaveCallback() {
    return (entity, s) -> {
      if (entity.getId() == null) {
        entity.setId(UUID.randomUUID());
      }
      return entity;
    };
  }

  @WritingConverter
  static class ZonedDateTimeToTimestampWritingConverter implements Converter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convert(@NotNull ZonedDateTime source) {
      return Timestamp.valueOf(source.toLocalDateTime());
    }
  }

  @ReadingConverter
  static class TimestampToZonedDateTimeWritingConverter implements Converter<Timestamp, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(Timestamp source) {
      return source.toLocalDateTime().atZone(ZoneId.systemDefault());
    }
  }
}
