package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeError {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErrorValidacao>> tratarErro400(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        System.out.println(erros);
        var listErrors = erros.stream().map(DadosErrorValidacao::new).toList();
        return ResponseEntity.badRequest().body(listErrors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensagemErro > forBidden(BadCredentialsException exception){

        var response = new MensagemErro(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

    }

    public record DadosErrorValidacao(String campo, String mensagem){
        public DadosErrorValidacao(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    public record MensagemErro(String error) {}


}


