package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.ConsultaRepository;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentodeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiraHr = dados.data().withHour(7);
        var ultimaHr = dados.data().withHour(18);

        var consulta = repository.existsByIdPaciente_IdAndDataBetween(dados.idPaciente(), primeiraHr, ultimaHr);
        if(consulta){
            throw new ValidacaoException("Paciente já agendou nesse dia");
        }
    }
}
