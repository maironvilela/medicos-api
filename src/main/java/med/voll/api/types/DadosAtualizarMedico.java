package med.voll.api.types;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco
) {
}
