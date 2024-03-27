package com.api.domain.consultas;

import com.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consultas, Long> {

    boolean existsByIdMedico_IdAndData(Long Id, LocalDateTime data);

    @Query("""
    select c from Consultas c where c.id = :Id and c.data between :primeiraHr and :ultimaHr
""")
    Consultas findPacienteAtividade(Long Id, LocalDateTime primeiraHr, LocalDateTime ultimaHr);
}