package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.ConsultaRepository;
import com.api.domain.consultas.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentodeConsulta{

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiraHr = dados.data().withHour(7);
        var ultimaHr = dados.data().withHour(18);

        var consulta = repository.findPacienteAtividade(dados.idPaciente(), primeiraHr, ultimaHr);
        if(consulta != null){
            throw new ValidacaoException("Paciente já agendou nesse dia");
        }
    }
}
