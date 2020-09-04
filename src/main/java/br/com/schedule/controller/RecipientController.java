package br.com.schedule.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.schedule.dto.RecipientDataTransferObject;
import br.com.schedule.service.recipient.RecipientService;

@RequestMapping("api/recipient")
@RestController
public class RecipientController {

  @Autowired
  private RecipientService service;

  @GetMapping(path = "{recipient}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RecipientDataTransferObject> find(
      @PathVariable(value = "recipient") final String recipient) {
    return ResponseEntity.ok(service.find(recipient));
  }

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RecipientDataTransferObject>> find() {
    return ResponseEntity.ok(service.find());
  }

  @DeleteMapping(path = "/")
  public ResponseEntity<Object> delete(@RequestParam(value = "recipient") final String recipient) {
    service.delete(recipient);
    return ResponseEntity.noContent().build();
  }

}
