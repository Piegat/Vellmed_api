package com.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Page<Paciente> findAllByAtivo(boolean ativo, Pageable paginacao);

    @Query("""
    select p from pacientes p
    where
    p.id = :id
    and 
    p.ativo = true
""")
    Paciente findByAtivoId(Long id);
}
