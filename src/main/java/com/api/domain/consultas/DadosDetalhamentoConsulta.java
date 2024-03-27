package com.api.domain.consultas;

import com.api.domain.medico.Medico;
import com.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long idMedico, Long idPaciente, LocalDateTime data) {
}
