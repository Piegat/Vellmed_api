package com.api.domain.paciente;

import com.api.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pacientes")
@Entity(name = "pacientes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;

        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizarPaciente dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if (dados.telefone()!= null) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null){
            this.endereco.atualizardados(dados.endereco());
        }



    }

    public void excluir() {
        this.ativo = false;
    }
}
