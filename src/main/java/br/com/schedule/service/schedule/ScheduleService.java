package br.com.schedule.service.schedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.dto.ScheduleDataTransferObject;

public interface ScheduleService {
  Schedule save(ScheduleDataTransferObject dto);

  void delete(String uuid);

  Page<ScheduleDataTransferObject> find(String status, Pageable pageable);
}
