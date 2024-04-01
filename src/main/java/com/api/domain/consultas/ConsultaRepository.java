package com.api.domain.consultas;

import com.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consultas, Long> {

    @Query("""
    select c from Consultas c where c.idMedico.id = :id and c.data = :data
""")
    public Consultas findByMedicoLivre(@Param("id") Long id, @Param("data") LocalDateTime data);


    boolean existsByIdPaciente_IdAndDataBetween(Long Id, LocalDateTime primeiraHr, LocalDateTime ultimaHr);
}