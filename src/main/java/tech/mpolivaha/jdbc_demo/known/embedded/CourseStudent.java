package tech.mpolivaha.jdbc_demo.known.embedded;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

@Table
public record CourseStudent(
  @Id Long id,
  String name,
  double gpa,
  @Embedded(onEmpty = OnEmpty.USE_NULL, prefix = "address_") Address address) { }
