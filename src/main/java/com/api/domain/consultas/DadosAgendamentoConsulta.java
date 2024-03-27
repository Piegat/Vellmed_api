package com.api.domain.consultas;

import com.api.domain.medico.Especialidade;
import com.api.domain.medico.Medico;
import com.api.domain.paciente.Paciente;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long idMedico,

                                       @NotNull
                                       Long idPaciente,

                                       @NotNull
                                       @Future
                                       LocalDateTime data,

                                       Especialidade especialidade) {
}
