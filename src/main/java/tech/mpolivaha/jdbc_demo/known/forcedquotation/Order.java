package tech.mpolivaha.jdbc_demo.known.forcedquotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Accessors(chain = true)
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  private Long id;

  private String name;

  @Column("count")
  private Integer count;
}
