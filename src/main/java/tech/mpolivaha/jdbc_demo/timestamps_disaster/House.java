package tech.mpolivaha.jdbc_demo.timestamps_disaster;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class House {

  @Id
  private Long id;

  private LocalDate buildIn;
}
