package tech.mpolivaha.jdbc_demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tech.mpolivaha.jdbc_demo.known.quety_mapping_is_broken.ComplexEntity;

@Component
public class ComplexEntityRowMapper implements RowMapper<ComplexEntity> {

  @Override
  public ComplexEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new ComplexEntity(
        rs.getLong("ID"),
        parseProperties(rs.getString("PROPERTIES"))
    );
  }

  private Map<String, String> parseProperties(String value) {
    if (value == null) return Map.of();

    Map<String, String> result = new HashMap<>();

    for (String s : value.split(";")) {
      String[] keyValue = s.split("=");
      result.put(keyValue[0], keyValue[1]);
    }

    return result;
  }
}
