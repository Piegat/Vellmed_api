package com.api.domain.consultas.validacoes;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.ConsultaRepository;
import com.api.domain.consultas.Consultas;
import com.api.domain.consultas.DadosAgendamentoConsulta;
import com.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component

public class ValidadorMedicoDisponivel implements ValidadorAgendamentodeConsulta {

    @Autowired
    private ConsultaRepository repository ;

    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DadosAgendamentoConsulta dados){


        var medico = repository.existsByIdMedico_IdAndData(dados.idMedico(), dados.data());
        if(medico){
            throw  new ValidacaoException("Medico ocupado");
        }

    }}

