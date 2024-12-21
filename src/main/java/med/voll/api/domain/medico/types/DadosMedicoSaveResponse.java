package med.voll.api.domain.medico.types;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.modelos.Medico;

public record DadosMedicoSaveResponse(Long id, String nome, String telefone ,String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosMedicoSaveResponse (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getTelefone(),medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
