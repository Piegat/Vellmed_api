package com.api.domain.paciente;

import com.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente(

        @NotBlank(message = "Nome não informado")
        String nome,

        @NotBlank(message = "E-mail não informado")
        @Email(message = "E-mail invalido")
        String email,
        @NotBlank(message = "telefone não infomado")
        @Pattern(regexp = "\\d{8,11}")
        String telefone,
        @NotBlank(message = "CPF não informado")
        @CPF(message = "CPF INVALIDO")
        String cpf,

        @NotNull @Valid
        DadosEndereco endereco) {
}
