package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        System.out.println("CONTROLLER EFETUAR LOGIN");
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
          var usuarioAutenticado = this.manager.authenticate(authenticationToken);
         var tokenJWT = this.tokenService.gerarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println(tokenJWT);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
