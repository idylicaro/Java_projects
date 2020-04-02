package Testes;

import org.autenticador.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    public void usuarioFormatoErrado1tracinhos(){
        ArquivoDeAutenticacaoInvalidoException exception = assertThrows(ArquivoDeAutenticacaoInvalidoException.class,
                ()->new Autenticador("src/usuarioErrado2.txt") );
        assertEquals("Formato errado:maria-12341none",exception.getMessage());
    }
    @Test
    public void testeAutenticacomSucesso() throws AutenticacaoException {
        Autenticador a = new Autenticador("src/fileTeste");
        Usuario u = a.autenticar("idylicaro","12345");
        assertEquals("admin",u.getAcesso());
        assertEquals("idylicaro",u.getLogin());
    }
    @Test
    public void testeAutenticacomLoginInvalido()  {
        Autenticador a = new Autenticador("src/fileTeste");
        AutenticacaoException exception = assertThrows(AutenticacaoException.class,
                ()->a.autenticar("naoexiste","12345"));
        assertTrue(exception instanceof UsuarioInvalidoException);
        assertEquals("naoexiste",exception.getLogin());
    }
    
}