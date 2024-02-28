package tech.mpolivaha.jdbc_demo.known.customconversions;

import java.math.BigDecimal;
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
public class Currency {

  @Id
  private Long id;

  private BigDecimal exchangeRate;
}
