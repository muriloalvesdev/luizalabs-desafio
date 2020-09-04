package br.com.schedule.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

  Optional<Schedule> findByRecipient(Recipient find);

}
