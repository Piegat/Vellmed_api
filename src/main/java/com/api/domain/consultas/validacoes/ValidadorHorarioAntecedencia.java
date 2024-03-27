package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentodeConsulta {
    public void validar(DadosAgendamentoConsulta dados) {

            var dataConsulta = dados.data();
            var agora = LocalDateTime.now();
            var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

            if (diferencaEmMinutos <30){
                throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30 minutos");
            }
    }
    }
