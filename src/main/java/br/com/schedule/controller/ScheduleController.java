package br.com.schedule.controller;

import br.com.schedule.dto.ScheduleDataTransferObject;
import br.com.schedule.service.schedule.ScheduleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RequestMapping("api/schedule")
@RestController
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class ScheduleController {

  private final ScheduleService<ScheduleDataTransferObject> service;
  private final String PATH_BY_ID = "/{uuid}";

  @GetMapping(path = PATH_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ScheduleDataTransferObject> find(@PathVariable String uuid) {
    return service.find(uuid).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> save(@Validated @RequestBody ScheduleDataTransferObject dto) {
    return service
        .save(dto)
        .map(this::getCreatedResponse)
        .orElse(ResponseEntity.unprocessableEntity().build());
  }

  private ResponseEntity<Object> getCreatedResponse(UUID uuid) {
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/schedule/{uuid}")
                .buildAndExpand(uuid)
                .toUri())
        .build();
  }

  @DeleteMapping(path = PATH_BY_ID)
  public ResponseEntity<ScheduleDataTransferObject> delete(@PathVariable String uuid) {
    return service.delete(uuid).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
  }
}
