package com.api.domain.medico;

import com.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DadosCadastroMedico(
        @NotBlank(message = "Nome não informado")
        String nome,

        @NotBlank(message = "Telefone não informado")
        @Pattern(regexp = "\\d{8,11}")
        String telefone,

        @NotBlank(message = "E-mail não informado")
        @Email (message = "E-mail incorreto")
        String email,

        @NotBlank(message = "CRM Não informado!")
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull(message = "Especialidade não informada")
        Especialidade especialidade,

        @NotNull @Valid
        DadosEndereco endereco) {

}
