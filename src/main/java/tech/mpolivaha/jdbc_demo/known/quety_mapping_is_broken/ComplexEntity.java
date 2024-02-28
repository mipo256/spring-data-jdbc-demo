package tech.mpolivaha.jdbc_demo.known.quety_mapping_is_broken;

import java.util.Map;
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
public class ComplexEntity {

  @Id
  private Long id;

  private Map<String, String> properties;
}
