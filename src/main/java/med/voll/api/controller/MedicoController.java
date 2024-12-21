package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.MedicoRepositorio;
import med.voll.api.domain.medico.types.*;
import med.voll.api.domain.medico.modelos.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosMedicoSaveResponse> cadastrar(@RequestBody @Valid DadosCadastroMedico dadosMedico, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dadosMedico);

        this.repositorio.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        var medicoDtoResponse = new DadosMedicoSaveResponse(medico);

         return ResponseEntity.created(uri).body(medicoDtoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = authentication.getName();
        System.out.println(username);
        System.out.println("GetMapping");
       var medicos = this.repositorio.findAllByIsAtivoTrue(pageable).map(DadosListagemMedico::new);
       return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosMedicosUpdateResponse> atualizar(@PathVariable Long id , @RequestBody DadosAtualizarMedico dadosAtualizarMedico){
        var medico = this.repositorio.getReferenceById(id);
        medico.atualizarInformacoes(dadosAtualizarMedico);

        return ResponseEntity.ok(new DadosMedicosUpdateResponse(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var medico = this.repositorio.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
     public ResponseEntity<DadosMedicosGetByIdResponse> getById(@PathVariable Long id){
        var medico = this.repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DadosMedicosGetByIdResponse(medico));
    }
}
