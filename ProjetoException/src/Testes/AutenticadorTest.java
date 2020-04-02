package Testes;

import org.autenticador.ArquivoDeAutenticacaoInvalidoException;
import org.autenticador.Autenticador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AutenticadorTest {
    @Test
    public void arquivoNaoExistente(){
        assertThrows(ArquivoDeAutenticacaoInvalidoException.class, () -> {
            Autenticador a = new Autenticador("naoExiste.txt");
        });
    }
    @Test
    public void usuarioFormatoErrado3tracinhos(){
        ArquivoDeAutenticacaoInvalidoException exception = assertThrows(ArquivoDeAutenticacaoInvalidoException.class,
                ()->new Autenticador("src/usuarioFormatoErradoTeste.txt") );
            assertEquals("Formato errado:raulzinho-123-45-regular",exception.getMessage());


    }
}