package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.MedicoRepositorio;
import med.voll.api.modelos.Medico;
import med.voll.api.types.DadosAtualizarMedico;
import med.voll.api.types.DadosCadastroMedico;
import med.voll.api.types.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;


    @PostMapping
    @Transactional
    public DadosCadastroMedico cadastrar(@RequestBody @Valid  DadosCadastroMedico dadosMedico){
        this.repositorio.save(new Medico(dadosMedico));
         return dadosMedico;
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable){
       // return this.repositorio.findAll(pageable).map(DadosListagemMedico::new);
        return this.repositorio.findAllByIsAtivoTrue(pageable).map(DadosListagemMedico::new);

    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id ,@RequestBody DadosAtualizarMedico dadosAtualizarMedico){
        var medico = this.repositorio.getReferenceById(id);
        medico.atualizarInformacoes(dadosAtualizarMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        var medico = this.repositorio.getReferenceById(id);
        medico.excluir();
        //this.repositorio.deleteById(id);
    }
}
