package com.api.domain.consultas;

import com.api.domain.ValidacaoException;
import com.api.domain.consultas.validacoes.ValidadorAgendamentodeConsulta;
import com.api.domain.medico.Medico;
import com.api.domain.medico.MedicoRepository;
import com.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentodeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente não existe.");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico não existe.");

        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não existe medico disponivel nessa data.");

        }

        var consulta = new Consultas(null, paciente, medico, dados.data());
        repository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta.getId(), consulta.getIdMedico().getId(), consulta.getIdPaciente().getId(), consulta.getData());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if( dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigadoria quando medico não é escolhida");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
