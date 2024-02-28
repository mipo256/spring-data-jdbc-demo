package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.CustomConversionsTest_2.CurrentConfig;
import tech.mpolivaha.jdbc_demo.known.customconversions.Currency;
import tech.mpolivaha.jdbc_demo.known.customconversions.CurrencyRepository;
import tech.mpolivaha.jdbc_demo.known.customconversions.Location;
import tech.mpolivaha.jdbc_demo.known.customconversions.LocationRepository;

@SpringBootTest(classes = Application.class)
@Import({CurrentConfig.class})
public class CustomConversionsTest_2 extends AbstractIntegrationTest {

  @Configuration
  static class CurrentConfig extends AbstractJdbcConfiguration {

    private static BigDecimalRoundingWritingConverter FROM_APP_TO_DB = new BigDecimalRoundingWritingConverter();

    private static BigDecimalRoundingReadingConverter FROM_DB_TO_APP = new BigDecimalRoundingReadingConverter();

    @WritingConverter
    static class BigDecimalRoundingWritingConverter implements Converter<BigDecimal, BigDecimal> {

      @Override
      public BigDecimal convert(BigDecimal source) {
        if (source == null) {
          return null;
        } else {
          return source.setScale(2, RoundingMode.HALF_UP);
        }
      }
    }

    @ReadingConverter
    static class BigDecimalRoundingReadingConverter implements Converter<BigDecimal, BigDecimal> {

      @Override
      public BigDecimal convert(BigDecimal source) {
        return source;
      }
    }

    @Bean
    public JdbcCustomConversions jdbcCustomConversions() {
      return new JdbcCustomConversions(
          List.of(
              FROM_APP_TO_DB,
              FROM_DB_TO_APP
          )
      );
    }
  }

  @Autowired
  private LocationRepository locationRepository;

  @Autowired
  private CurrencyRepository currencyRepository;

  @Test
  void whenCurrencyIsSaved_thenRateOsTruncated() {
    Currency entity = new Currency()
        .setExchangeRate(BigDecimal.valueOf(89.8238));

    Long id = currencyRepository.save(entity).getId();

    assertThat(currencyRepository.findById(id).map(Currency::getExchangeRate)).hasValue(BigDecimal.valueOf(89.82));
  }

  @Test
  void whenSavingLocation_thenCoordinatesAlsoTruncated() {
    Location entity = new Location()
        .setLatitude(BigDecimal.valueOf(34.8912738919))
        .setLongitude(BigDecimal.valueOf(12.7428392312));

    Long id = locationRepository.save(entity).getId();

    assertThat(locationRepository.findById(id)).hasValueSatisfying(location -> {
      assertThat(
          location.getLongitude().equals(BigDecimal.valueOf(12.74))
              && location.getLatitude().equals(BigDecimal.valueOf(34.89))
      ).isTrue();
    });
  }

}
