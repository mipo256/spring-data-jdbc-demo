package tech.mpolivaha.jdbc_demo.known.auditingannotations;

import java.util.Optional;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class MyCustomAuditAware implements AuditorAware<UUID> {

  @Override
  public @NotNull Optional<UUID> getCurrentAuditor() {
    return Optional.of(UUID.randomUUID());
  }
}
