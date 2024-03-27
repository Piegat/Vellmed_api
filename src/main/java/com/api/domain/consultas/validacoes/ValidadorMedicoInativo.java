package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import com.api.domain.medico.MedicoRepository;
import com.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo implements ValidadorAgendamentodeConsulta{

    @Autowired
    private MedicoRepository repository;
    public void validar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() == null){
            return;
        }

        var medico = repository.findByAtivoId(dados.idMedico());
        if(!medico.isAtivo()){
            throw new ValidacaoException("Consulta não pode ser agendada com medico inativo.");
        }


    }
}

