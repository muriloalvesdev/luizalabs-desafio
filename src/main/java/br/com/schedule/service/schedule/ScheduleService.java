package br.com.schedule.service.schedule;

import java.util.List;
import java.util.UUID;
import br.com.schedule.dto.ScheduleDataTransferObject;

public interface ScheduleService {

  UUID save(ScheduleDataTransferObject dto);

  ScheduleDataTransferObject find(String uuid);

  List<ScheduleDataTransferObject> find();

  void delete(String uuid);
}
