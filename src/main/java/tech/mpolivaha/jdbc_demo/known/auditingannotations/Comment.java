package tech.mpolivaha.jdbc_demo.known.auditingannotations;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Comment {

  @Id
  private Long id;

  private String content;

  @CreatedDate
  private Date addedAt;

  @CreatedBy
  private UUID addedBy;
}
