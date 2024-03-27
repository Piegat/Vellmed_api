package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentodeConsulta{

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var abertura = dataConsulta.getHour() < 7;
        var encerramento = dataConsulta.getHour() >18;

        if (domingo || abertura || encerramento){
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
        }
    }
}

