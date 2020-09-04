package br.com.schedule.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.schedule.domain.model.entity.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
  Optional<Recipient> findByRecipient(String recipient);
}
