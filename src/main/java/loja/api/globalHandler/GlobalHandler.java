package loja.api.globalHandler;

import loja.api.exceptions.BusinessException;
import loja.api.exceptions.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessException(CategoryException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<String> categoryException(CategoryException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidation(MethodArgumentNotValidException e) {
        List<String> erros = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Ocorreu um erro inesperado, contate nosso suporte");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runTimeException(Exception e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Ocorreu um erro inesperado, contate nosso suporte");
    }

}
