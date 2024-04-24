package tech.mpolivaha.jdbc_demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.timestamps_disaster.Flight;
import tech.mpolivaha.jdbc_demo.timestamps_disaster.FlightRepository;
import tech.mpolivaha.jdbc_demo.timestamps_disaster.House;
import tech.mpolivaha.jdbc_demo.timestamps_disaster.HouseRepository;
import tech.mpolivaha.jdbc_demo.timestamps_disaster.OffsetDateTimeEntityRepository;

@Slf4j
@SpringBootTest(classes = Application.class)
public class TimestampsDisasterTest extends AbstractIntegrationTest {

  @Autowired
  private HouseRepository houseRepository;

  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private OffsetDateTimeEntityRepository offsetDateTimeEntityRepository;

  @Test
  void whenSavingTimestamp_thenSeeWhatHappens() {
    LocalDate buildIn = LocalDate.parse("1580-08-12");

    House saved = houseRepository.save(new House().setBuildIn(buildIn));

    assertThat(saved.getBuildIn()).isEqualTo(buildIn);

    LocalDate buildInDb = houseRepository.findById(saved.getId()).map(House::getBuildIn).orElse(null);

    assertThat(buildInDb).isEqualTo(buildIn);
  }

  @Test
  void convertTimestampToLocalDate() {
    Instant date = Instant.parse("1582-10-16T00:00:00Z");

    Timestamp ts = new Timestamp(date.toEpochMilli());

    LocalDateTime localDateTime = ts.toLocalDateTime();

    assertThat(localDateTime.toLocalDate()).isEqualTo(LocalDate.parse("1582-10-16"));
  }

  @Test
  void convertTimestampToLocalDate_() {
    Instant date = Instant.parse("1582-09-16T00:00:00Z");

    Timestamp ts = new Timestamp(date.toEpochMilli());

    LocalDateTime localDateTime = ts.toLocalDateTime();

    assertThat(localDateTime.toLocalDate()).isEqualTo(LocalDate.parse("1582-09-16"));
  }

  @Test
  void whenSavingZonedDateTimeJava_thenWatchingWhatHappens() {
    ZonedDateTime parse = ZonedDateTime.now();

    Flight saved = flightRepository.save(new Flight().setArrivedAt(parse));

    assertThat(saved.getArrivedAt()).isEqualTo(parse);

    ZonedDateTime localDateTime = flightRepository.findById(saved.getId()).map(Flight::getArrivedAt).orElse(null);

    assertThat(localDateTime).isEqualTo(parse);
  }
}
