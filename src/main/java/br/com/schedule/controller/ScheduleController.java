package br.com.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.service.schedule.ScheduleService;

@RequestMapping("api/schedule")
@RestController
class ScheduleController {

  @Autowired
  private ScheduleService service;

  @GetMapping(path = "{status}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Page<ScheduleDataTransferObject>> find(
      @PathVariable(name = "status") String status, Pageable pageable) {
    return ResponseEntity.ok(service.find(status, pageable));
  }

  @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> save(@Validated @RequestBody ScheduleDataTransferObject dto) {
    Schedule schedule = service.save(dto);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/schedule/{status}").buildAndExpand(schedule.getStatus().name()).toUri())
        .body(schedule.getUuid());
  }

  @DeleteMapping(path = "/{uuid}")
  public ResponseEntity<Object> delete(@PathVariable(name = "uuid") String uuid) {
    service.delete(uuid);
    return ResponseEntity.noContent().build();
  }
}
