package tech.mpolivaha.jdbc_demo.timestamps_disaster;

import java.time.OffsetDateTime;
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
public class OffsetDateTimeEntity {

  @Id
  private Long id;

  private OffsetDateTime offsetDateTimeField;
}
