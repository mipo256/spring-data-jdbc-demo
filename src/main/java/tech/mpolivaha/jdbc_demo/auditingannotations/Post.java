package tech.mpolivaha.jdbc_demo.auditingannotations;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Post {

  @Id
  private Long id;

  @MappedCollection(idColumn = "post_id", keyColumn = "id")
  private List<Comment> comments;

  @CreatedDate
  private Date createdAt;
}
