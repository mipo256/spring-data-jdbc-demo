package tech.mpolivaha.jdbc_demo.customconversions;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Accessors(chain = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Location {

  @Id
  private Long id;
  private BigDecimal longitude;
  private BigDecimal latitude;
}
