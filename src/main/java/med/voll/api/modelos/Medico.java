package med.voll.api.modelos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.types.DadosAtualizarMedico;
import med.voll.api.types.DadosCadastroMedico;
import med.voll.api.types.Especialidade;

@Entity(name = "Medico")
@Table(name="medicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Boolean isAtivo;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico dadosMedico) {
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.crm = dadosMedico.crm();
        this.endereco = new Endereco(dadosMedico.endereco());
        this.especialidade = dadosMedico.especialidade();
        this.telefone = dadosMedico.telefone();
        this.isAtivo = true;

     }

    public void atualizarInformacoes(@Valid DadosAtualizarMedico dadosAtualizarMedico) {
        this.nome = dadosAtualizarMedico.nome() ==null ?this.nome: dadosAtualizarMedico.nome();
        this.telefone = dadosAtualizarMedico.telefone() == null ?this.telefone: dadosAtualizarMedico.telefone();
        this.endereco = dadosAtualizarMedico.endereco() == null? this.endereco: this.endereco.atualizarInformacoes(dadosAtualizarMedico.endereco());
     }

    public void excluir() {
        this.isAtivo = false;
    }
}
