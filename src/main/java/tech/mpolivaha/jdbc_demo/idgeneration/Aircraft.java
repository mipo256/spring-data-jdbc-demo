package tech.mpolivaha.jdbc_demo.idgeneration;

import java.util.UUID;
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
public class Aircraft {

  @Id
  private UUID id;

  private String model;

  public Aircraft(String model) {
    this.model = model;
  }
}
