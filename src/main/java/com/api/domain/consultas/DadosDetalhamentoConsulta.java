package com.api.domain.consultas;

import com.api.domain.medico.DadosDetelhamentoMedico;
import com.api.domain.medico.Medico;
import com.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,Long idMedico, Long idPaciente, LocalDateTime data) {

  public DadosDetalhamentoConsulta(Consultas consultas){
    this(consultas.getId(), consultas.getIdMedico().getId(), consultas.getIdPaciente().getId(), consultas.getData());
}
}
