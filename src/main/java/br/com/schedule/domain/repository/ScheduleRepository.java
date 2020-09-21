package br.com.schedule.domain.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.schedule.domain.model.entity.Recipient;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
  Optional<Schedule> findByUuidAndStatus(UUID uuid, Status status);
}
