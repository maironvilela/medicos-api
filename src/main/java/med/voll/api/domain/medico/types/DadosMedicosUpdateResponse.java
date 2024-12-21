package med.voll.api.domain.medico.types;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.modelos.Medico;

public record DadosMedicosUpdateResponse(Long id, String nome, String telefone ,String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosMedicosUpdateResponse (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getTelefone(),medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
