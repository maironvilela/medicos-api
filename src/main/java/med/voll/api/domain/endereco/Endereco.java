package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
 import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
 public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private Integer numero;
    private String complemento;
    private String cidade;
    private String uf;


    public Endereco(DadosEndereco dadosEndereco) {
       this.logradouro = dadosEndereco.logradouro();
       this.bairro = dadosEndereco.bairro();
       this.cep = dadosEndereco.cep();
       this.numero = Integer.parseInt(dadosEndereco.numero());
       this.complemento = dadosEndereco.complemento();
      this.cidade = dadosEndereco.cidade();
      this.uf = dadosEndereco.uf();
    }

    public Endereco atualizarInformacoes(@Valid DadosEndereco endereco) {
       this.logradouro = endereco.logradouro() == null ?this.logradouro: endereco.logradouro();
       this.bairro = endereco.bairro() == null?this.bairro: endereco.logradouro();
       this.cep = endereco.cep() == null ?this.cep: endereco.logradouro();
       this.cidade = endereco.cidade() == null?this.cidade: endereco.logradouro();
       this.uf = endereco.uf() == null ?this.uf: endereco.logradouro();

       return this;
    }
}
