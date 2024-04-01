package com.api.domain.consultas;

import com.api.domain.medico.Especialidade;
import lombok.With;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AgendaDeConsultaTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;


    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsulta agenda;




    @Test
    @DisplayName("Deveria devolver código 400 quando informações invalidas")
    void agendarCenario1() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informações validas")
    void agendarCenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);

        var dados = new DadosDetalhamentoConsulta(null, 1L, 1L, data);

        when(agenda.agendar(any())).thenReturn(dados);


        var response = mvc.perform(post("/consultas").contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoConsultaJson.write(
                        new DadosAgendamentoConsulta(1L, 1L, data, Especialidade.CARDIOLOGIA)).getJson()))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dados).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }
}