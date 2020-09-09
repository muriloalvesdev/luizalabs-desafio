package br.com.schedule.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import br.com.schedule.ConstantsTests;
import br.com.schedule.domain.model.entity.Schedule;
import br.com.schedule.domain.model.entity.Status;
import br.com.schedule.domain.repository.ScheduleRepository;

@DisplayName("Classe responsável por efetuar um teste de integração")
@SpringBootTest
@AutoConfigureMockMvc
@Profile(ConstantsTests.PROFILE_TEST)
class ScheduleControllerIntegrationTests {

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private ScheduleRepository scheduleRepository;

  private MockMvc mockMvc;

  private String requestBody;

  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    this.requestBody =
        "{\n" + "    \"message\": \"VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!\",\n"
            + "    \"recipient\": {\n" + "        \"recipient\": \"muriloalvesdev.ti@gmail.com\"\n"
            + "    },\n" + "    \"send_date\": \"2020-11-01 23:59:59\",\n"
            + "    \"type\": \"email\"\n" + "}";
  }

  @DisplayName("Deve enviar uma requisição para salvar um Schedule e "
      + "validar se o status retornado é created, também vai validar "
      + "se existe um campo Location no header do response")
  @Test
  @Order(1)
  void shouldCreateSchedulingRequest() throws Exception {
    this.mockMvc
        .perform(post("/api/schedule/").content(this.requestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated()).andExpect(header().exists("Location"));
  }

  @DisplayName("Deve buscar o Schedule salvo no primeiro teste e valdiar se os campos contidos no json")
  @Test
  @Order(2)
  void shouldSearchSavedSchedule() throws Exception {
    mockMvc.perform(get("/api/schedule/pending").contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.content[0].recipient.recipient", is("muriloalvesdev.ti@gmail.com")))
        .andExpect(
            jsonPath("$.content[0].message", is("VOCÊ FOI APROVADO EM NOSSO PROCESSO SELETIVO!")))
        .andExpect(jsonPath("$.content[0].type", is("EMAIL")))
        .andExpect(jsonPath("$.content[0].status", is("PENDING")))
        .andExpect(jsonPath("$.content[0].send_date", is("2020-11-01 23:59:59")))
        .andExpect(status().isOk());
  }

  @DisplayName("Deve buscar o UUID do schedule no banco de dados e enviar o mesmo para deletar logicamente através de uma requisição")
  @Test
  @Order(3)
  void shouldDeleteSchedule() throws Exception {
    Schedule schedule = scheduleRepository.findAll().get(0);
    assertNotNull(schedule);

    String uuid = schedule.getUuid().toString();

    this.mockMvc
        .perform(delete("/api/schedule/" + uuid).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNoContent());

    Schedule scheduleDeleted = scheduleRepository.findAll().get(0);
    assertNotNull(scheduleDeleted);
    assertTrue(scheduleDeleted.getStatus().equals(Status.DELETED));
  }
}
