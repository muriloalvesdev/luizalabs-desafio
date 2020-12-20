package br.com.schedule.service.schedule;

import java.util.Optional;
import java.util.UUID;

public interface ScheduleService<T> {
  Optional<UUID> save(T dto);

  Optional<T> delete(String uuid);

  Optional<T> find(String uuid);
}
