package com.api.controller;

import com.api.domain.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacientesController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity cadastrarPaciente (@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {

            var paciente = new Paciente(dados);
            repository.save(paciente);

            var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getAll(Pageable paginacao){
        var page = repository.findAllByAtivo(true, paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok().body(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> getById (@PathVariable("id") Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizarPaciente dados){
            var paciente = repository.getReferenceById(dados.id());
            paciente.atualizarInformacoes(dados);
            return ResponseEntity.ok().body(new DadosDetalhamentoPaciente(paciente));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable("id") Long id){
            var paciente = repository.getReferenceById(id);
            paciente.excluir();

            return ResponseEntity.noContent().build();

    }
}
