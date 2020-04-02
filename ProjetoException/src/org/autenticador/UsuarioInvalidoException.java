package org.autenticador;

public class UsuarioInvalidoException extends AutenticacaoException {
    public UsuarioInvalidoException(String message, String login) {
        super(message, login);
    }
}
