package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import com.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteInativo implements ValidadorAgendamentodeConsulta{

    @Autowired
    private PacienteRepository repository;
    public void validar(DadosAgendamentoConsulta dados) {

        if(dados.idPaciente() == null){
            return;
        }

        var paciente = repository.findByAtivoId(dados.idPaciente());
        if(!paciente.isAtivo()){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo.");
        }


    }
    }
