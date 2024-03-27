package com.api.domain.medico;

import com.api.domain.endereco.Endereco;

public record DadosDetelhamentoMedico(Long id, String nome, String email, String crm, String telefone ,Especialidade especialidade, Endereco endereco) {

    public DadosDetelhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone() ,medico.getEspecialidade(), medico.getEndereco());
    }

}
