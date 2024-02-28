package tech.mpolivaha.jdbc_demo.known;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.mpolivaha.jdbc_demo.AbstractIntegrationTest;
import tech.mpolivaha.jdbc_demo.Application;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.Flight;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.FlightRepository;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.House;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.HouseRepository;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.OffsetDateTimeEntity;
import tech.mpolivaha.jdbc_demo.known.timestamps_disaster.OffsetDateTimeEntityRepository;

@Slf4j
@SpringBootTest(classes = Application.class)
public class TimestampsDisasterTest_5 extends AbstractIntegrationTest {

  @Autowired
  private HouseRepository houseRepository;

  @Autowired
  private FlightRepository flightRepository;

  @Autowired
  private OffsetDateTimeEntityRepository offsetDateTimeEntityRepository;

  @Test
  void whenSavingTimestamp_thenSeeWhatHappens() {
    LocalDate buildIn = LocalDate.parse("1500-08-12");

    House saved = houseRepository.save(new House().setBuildIn(buildIn));

    assertThat(saved.getBuildIn()).isEqualTo(buildIn);

    LocalDate buildInDb = houseRepository.findById(saved.getId()).map(House::getBuildIn).orElse(null);

    assertThat(buildInDb).isEqualTo(buildIn);
  }

  @Test
  void whenSavingZonedDateTimeJava_thenWatchingWhatHappens() {
    ZonedDateTime parse = ZonedDateTime.now();

    Flight saved = flightRepository.save(new Flight().setArrivedAt(parse));

    assertThat(saved.getArrivedAt()).isEqualTo(parse);

    ZonedDateTime localDateTime = flightRepository.findById(saved.getId()).map(Flight::getArrivedAt).orElse(null);

    assertThat(localDateTime).isEqualTo(parse);
  }

  @Test
  void whenSavingOffsetDateTimeJava_thenWatchingTheWorldBurn() {
    OffsetDateTime parse = OffsetDateTime.now();

    OffsetDateTimeEntity saved = offsetDateTimeEntityRepository.save(new OffsetDateTimeEntity().setOffsetDateTimeField(parse));

    assertThat(saved.getOffsetDateTimeField()).isEqualTo(parse);

    OffsetDateTime localDateTime = offsetDateTimeEntityRepository.findById(saved.getId()).map(OffsetDateTimeEntity::getOffsetDateTimeField).orElse(null);

    assertThat(localDateTime).isEqualTo(parse);
  }
}
